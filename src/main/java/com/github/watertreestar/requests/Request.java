package com.github.watertreestar.requests;

import com.github.watertreestar.requests.constant.HttpMethod;
import com.github.watertreestar.requests.session.SessionContext;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.Collection;
import java.util.Map;

public class Request {
    private final HttpMethod method;
    private final URL url;
    private final Collection<? extends Map.Entry<String, ?>> headers;
    private final Collection<? extends Map.Entry<String, ?>> cookies;
    private final Collection<? extends Map.Entry<String, ?>> parameters;
    private final String userAgent;
    private final Charset charset;
    private final int connectTimeout;
    private final int readTimeout;
    private final int writeTimeout;
    @Nullable
    private final Proxy proxy;
    private final SessionContext sessionContext;
    private final RequestBody<?> body;
    private final boolean verify;
    private final KeyStore keyStore;
    private final BasicAuth basicAuth;
    private final boolean useCompress;
    private final boolean keepAlive;
    private final boolean redirect;


    public Request(RequestBuilder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.headers = builder.headers;
        this.cookies = builder.cookies;
        this.parameters = builder.params;
        this.userAgent = builder.userAgent;
        this.charset = builder.charset;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.proxy = builder.proxy;
        this.sessionContext = builder.sessionContext;
        this.body = builder.requestBody;
        this.verify = builder.verify;
        this.keyStore = builder.keyStore;
        this.basicAuth = builder.basicAuth;
        this.useCompress = builder.useCompress;
        this.keepAlive = builder.keepAlive;
        this.redirect = builder.redirect;
    }

    public HttpMethod method() {
        return this.method;
    }

    public String  methodName() {
        return this.method.getName();
    }

    public URL url() {
        return this.url;
    }

    public Collection<? extends Map.Entry<String, ?>> headers() {
        return this.headers;
    }

    public Collection<? extends Map.Entry<String, ?>> parameters() {
        return this.parameters;
    }

    public Collection<? extends Map.Entry<String, ?>> cookies() {
        return this.cookies;
    }

    public String userAgent() {
        return this.userAgent;
    }

    public Charset charset() {
        return this.charset;
    }

    public int connectTimeout() {
        return this.connectTimeout;
    }

    public int readTimeout() {
        return this.readTimeout;
    }

    public int writeTimeout() {
        return this.writeTimeout;
    }

    public Proxy proxy() {
        return this.proxy;
    }

    public SessionContext sessionContext() {
        return this.sessionContext;
    }

    public RequestBody<?> body() {
        return this.body;
    }

    public boolean verify(){
        return this.verify;
    }

    public KeyStore keyStore() {
        return this.keyStore;
    }

    public BasicAuth basicAuth() {
        return this.basicAuth;
    }

    public boolean useCompress() {
        return this.useCompress;
    }

    public boolean keepAlive() {
        return this.keepAlive;
    }

    public boolean allowRedirect() {
        return this.redirect;
    }

    /**
     *  请求体
     * @param <T>
     */
    public static abstract class RequestBody<T> implements Serializable {
        private T body;
        private String contentType;
        private boolean withContentType;

        public RequestBody(T body, String contentType, boolean withContentType) {
            this.body = body;
            this.contentType = contentType;
            this.withContentType = withContentType;
        }

        public abstract void writeBody(OutputStream out, Charset charset) throws IOException;

        public T getBody() {
            return body;
        }

        public void setBody(T body) {
            this.body = body;
        }

        public T body() {
            return body;
        }

        public void body(T body){
            this.body = body;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getContentType() {
            return this.contentType;
        }

        public void contentType(String contentType) {
            this.contentType = contentType;
        }

        public String contentType() {
            return this.contentType;
        }

        public void setWithContentType(boolean withContentType) {
            this.withContentType = withContentType;
        }

        public void withContentType(boolean withContentType) {
            this.withContentType = withContentType;
        }

        public boolean isWithContentType() {
            return this.withContentType;
        }

        public boolean withContentType() {
            return this.withContentType;
        }
    }
}
