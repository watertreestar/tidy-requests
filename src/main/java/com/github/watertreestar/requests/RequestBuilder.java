package com.github.watertreestar.requests;

import com.github.watertreestar.requests.constant.HttpMethod;
import com.github.watertreestar.requests.exception.RequestsException;
import com.sun.istack.internal.Nullable;

import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class RequestBuilder {
    HttpMethod method = HttpMethod.GET;
    URL url;
    Collection<? extends Map.Entry<String, ?>> headers = new ArrayList<>();
    Collection<? extends Map.Entry<String, ?>> cookies = new ArrayList<>();
    Collection<? extends Map.Entry<String, ?>> params = new ArrayList<>();
    Charset charset = StandardCharsets.UTF_8;
    @Nullable
    Request.RequestBody<?> body;
    int readTimeout = 10000;
    int writeTimeout = 10000;
    int connectTimeout = 10000;
    String userAgent;
    @Nullable
    Proxy proxy;


    RequestBuilder() {
    }

    RequestBuilder(Request request) {

    }

    public RequestBuilder method(HttpMethod method) {
        this.method = Objects.requireNonNull(method);
        return this;
    }

    public RequestBuilder url(String url) {
        try {
            this.url = new URL(Objects.requireNonNull(url));
        } catch (MalformedURLException e) {
            throw new RequestsException("Resolve url error, url: " + url, e);
        }
        return this;
    }

    public RequestBuilder url(URL url) {
        this.url = Objects.requireNonNull(url);
        return this;
    }

    /**
     * Set request headers.
     */
    public RequestBuilder headers(Collection<? extends Map.Entry<String, ?>> headers) {
        this.headers = headers;
        return this;
    }

    /**
     * Set request headers.
     */
    @SafeVarargs
    public final RequestBuilder headers(Map.Entry<String, ?>... headers) {
        headers(Arrays.stream(headers).collect(Collectors.toList()));
        return this;
    }

    /**
     * Set request headers.
     */
    public final RequestBuilder headers(Map<String, ?> map) {
        this.headers = map.entrySet();
        return this;
    }

    /**
     * Set request cookies.
     */
    public RequestBuilder cookies(Collection<? extends Map.Entry<String, ?>> cookies) {
        this.cookies = cookies;
        return this;
    }

    /**
     * Set request cookies.
     */
    @SafeVarargs
    public final RequestBuilder cookies(Map.Entry<String, ?>... cookies) {
        cookies(Arrays.stream(cookies).collect(Collectors.toList()));
        return this;
    }

    /**
     * Set request cookies.
     */
    public final RequestBuilder cookies(Map<String, ?> map) {
        this.cookies = map.entrySet();
        return this;
    }


    /**
     * Set url query params.
     */
    public RequestBuilder params(Collection<? extends Map.Entry<String, ?>> params) {
        this.params = params;
        return this;
    }

    /**
     * Set url query params.
     */
    @SafeVarargs
    public final RequestBuilder params(Map.Entry<String, ?>... params) {
        this.params = Arrays.stream(params).collect(Collectors.toList());
        return this;
    }

    /**
     * Set url query params.
     */
    public final RequestBuilder params(Map<String, ?> map) {
        this.params = map.entrySet();
        return this;
    }

    /**
     * Set charset used to encode request params or forms. Default UTF8.
     */
    public RequestBuilder requestCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    /**
     * Set charset used to encode request params or forms. Default UTF8.
     */
    public RequestBuilder charset(Charset charset) {
        this.charset = charset;
        return this;
    }

    /**
     * Set request body
     */
    public RequestBuilder body(@Nullable Request.RequestBody<?> body) {
        this.body = body;
        return this;
    }

    public RequestBuilder userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public RequestBuilder connectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public RequestBuilder readTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public RequestBuilder writeTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }
}
