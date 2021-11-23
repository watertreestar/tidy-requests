package com.github.watertreestar.requests.executor;

import com.github.watertreestar.io.InputStreams;
import com.github.watertreestar.requests.*;
import com.github.watertreestar.requests.constant.HttpHeaders;
import com.github.watertreestar.requests.exception.RequestsException;
import com.github.watertreestar.requests.session.NoopSessionContext;
import com.github.watertreestar.requests.session.SessionContext;
import com.github.watertreestar.requests.util.CookieUtil;
import com.github.watertreestar.requests.util.SSLSocketMaker;
import com.github.watertreestar.requests.util.URLUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.watertreestar.requests.constant.StatusCode.*;

/**
 * 基于JDK UrlConnection实现Http请求
 */
public class UrlConnectionHttpExecutor implements HttpExecutor {
    @Override
    public Response proceed(Request request) throws RequestsException {
        // 1. 发起请求
        Response response = this.doRequest(request);
        int statusCode = response.statusCode();

        // todo 2. 处理响应 - 错误处理，重定向处理
        return response;
    }

    private static boolean isRedirect(int status) {
        return status == MULTIPLE_CHOICES || status == MOVED_PERMANENTLY || status == FOUND || status == SEE_OTHER
                || status == TEMPORARY_REDIRECT || status == PERMANENT_REDIRECT;
    }

    private Response doRequest(Request request) {
        // build request url
        Charset charset = request.charset();
        URL fullUrl = URLUtil.concatUrl(request.url(), request.charset(), URLUtil.entryToParameter(request.parameters()));

        Proxy proxy = request.proxy();
        if (proxy == null) {
            proxy = Proxy.NO_PROXY;
        }
        HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) fullUrl.openConnection(proxy);
        } catch (IOException e) {
            throw new RequestsException(e);
        }

        SessionContext sessionContext = request.sessionContext();
        if (sessionContext == null) {
            sessionContext = new NoopSessionContext();
        }
        List<Cookie> sessionCookies = sessionContext.cookies();
        if (!request.cookies().isEmpty() || !sessionCookies.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, ?> entry : request.cookies()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("; ");
            }
            for (Cookie cookie : sessionCookies) {
                sb.append(cookie.name()).append("=").append(cookie.value()).append("; ");
            }
            if (sb.length() > 2) {
                sb.setLength(sb.length() - 2);
                String cookieStr = sb.toString();
                conn.setRequestProperty(HttpHeaders.NAME_COOKIE, cookieStr);
            }
        }
        /**
         * 如果是HTTPS访问：
         * 1. 信任所有host
         * 2. 配置CA
         */
        // deal with https
        if (conn instanceof HttpsURLConnection) {
            HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
            if (!request.verify()) {
                SSLSocketFactory ssf = SSLSocketMaker.getTrustAllSSLSocketFactory();
                httpsConn.setSSLSocketFactory(ssf);
                // do not verify host of certificate
                httpsConn.setHostnameVerifier(NopHostnameVerifier.getInstance());
            } else if (request.keyStore() != null) {
                SSLSocketFactory ssf = SSLSocketMaker.getCustomTrustSSLSocketFactory(request.keyStore());
                httpsConn.setSSLSocketFactory(ssf);
            }
        }

        try {
            conn.setRequestMethod(request.methodName());
        } catch (ProtocolException e) {
            throw new RequestsException(e);
        }
        conn.setReadTimeout(request.readTimeout());
        conn.setConnectTimeout(request.connectTimeout());
        // Url connection did not deal with cookie when handle redirect. Disable it and handle it manually
        conn.setInstanceFollowRedirects(false);

        // headers
        if (!request.userAgent().isEmpty()) {
            conn.setRequestProperty(HttpHeaders.NAME_USER_AGENT, request.userAgent());
        }
        if (request.useCompress()) {
            conn.setRequestProperty(HttpHeaders.NAME_ACCEPT_ENCODING, "gzip, deflate");
        }

        if (request.basicAuth() != null) {
            conn.setRequestProperty(HttpHeaders.NAME_AUTHORIZATION, request.basicAuth().encode());
        }

        Request.RequestBody<?> body = request.body();
        if (body != null) {
            conn.setDoOutput(true);
            String contentType = body.contentType();
            if (contentType != null) {
                if (body.withContentType()) {
                    contentType += "; charset=" + request.charset().name().toLowerCase();
                }
                conn.setRequestProperty(HttpHeaders.NAME_CONTENT_TYPE, contentType);
            }
        }

        // set user custom headers
        for (Map.Entry<String, ?> header : request.headers()) {
            conn.setRequestProperty(header.getKey(), String.valueOf(header.getValue()));
        }

        // disable keep alive
        if (!request.keepAlive()) {
            conn.setRequestProperty("Connection", "close");
        }

        try {
            conn.connect();
        } catch (IOException e) {
            throw new RequestsException(e);
        }

        try {
            // send body
            if (body != null) {
                setBody(body, conn, charset);
            }
            return extractResponse(fullUrl, conn, sessionContext, request.methodName(), request);
        } catch (IOException e) {
            conn.disconnect();
            throw new RequestsException(e);
        } catch (Throwable e) {
            conn.disconnect();
            throw e;
        }
    }

    /**
     * Wrap response, deal with headers and cookies
     */
    private Response extractResponse(URL url, HttpURLConnection conn, SessionContext sessionContext, String method, Request request)
            throws IOException {
        // read result
        int status = conn.getResponseCode();
        String host = url.getHost().toLowerCase();

        String statusLine = null;
        // headers and cookies
        List<Header> headerList = new ArrayList<>();
        List<Cookie> cookies = new ArrayList<>();
        int index = 0;
        while (true) {
            String key = conn.getHeaderFieldKey(index);
            String value = conn.getHeaderField(index);
            if (value == null) {
                break;
            }
            index++;
            //status line
            if (key == null) {
                statusLine = value;
                continue;
            }
            headerList.add(new Header(key, value));
            if (key.equalsIgnoreCase(HttpHeaders.NAME_SET_COOKIE)) {
                Cookie c = CookieUtil.parseCookie(value, host, CookieUtil.calculatePath(url.getPath()));
                if (c != null) {
                    cookies.add(c);
                }
            }
        }
        Headers headers = new Headers(headerList);

        // 获取响应流
        InputStream input;
        try {
            input = conn.getInputStream();
        } catch (IOException e) {
            input = conn.getErrorStream();
        }
        if (input == null) {
            input = InputStreams.empty();
        }

        // update session
        sessionContext.storeCookie(cookies);
        return new Response(method, url.toExternalForm(), status, statusLine == null ? "" : statusLine,
                cookies, headers, input, request);
    }

    private void setBody(Request.RequestBody<?> body, HttpURLConnection conn, Charset requestCharset) {
        try (OutputStream os = conn.getOutputStream()) {
            body.writeBody(os, requestCharset);
        } catch (IOException e) {
            throw new RequestsException(e);
        }
    }
}
