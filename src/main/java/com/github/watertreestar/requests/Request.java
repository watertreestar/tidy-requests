package com.github.watertreestar.requests;

import com.github.watertreestar.requests.constant.HttpMethod;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
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
    }

    public HttpMethod method() {
        return this.method;
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

    public static abstract class RequestBody<T> implements Serializable {
        private T body;
        private String contentType;

        public RequestBody(T body, String contentType) {
            this.body = body;
            this.contentType = contentType;
        }

        public T getBody() {
            return body;
        }

        public void setBody(T body) {
            this.body = body;
        }

        public T body() {
            return body;
        }

        public void setContentType() {
            this.contentType = contentType;
        }
    }
}
