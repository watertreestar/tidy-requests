package com.github.watertreestar.requests;

import com.github.watertreestar.requests.constant.HttpMethods;

import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

public class Request {
    private final HttpMethods method;
    private final URL url;
    private final Collection<? extends Map.Entry<String, ?>> headers;
    private final Collection<? extends Map.Entry<String, ?>> cookies;
    private final Collection<? extends Map.Entry<String, ?>> parameters;
    private final String userAgent;
    private final Charset charset;
    private final int connectTimeout;
    private final int readTimeout;
    private final int writeTimeout;

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
