package com.github.watertreestar.requests;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Collections;
import java.util.List;

public class AbstractResponse {
    /**
     * 请求的url
     */
    protected final String url;
    /**
     * 响应状态码
     */
    protected final int statusCode;
    /**
     * 响应cookies
     */
    protected final List<Cookie> cookies;
    /**
     * 响应头
     */
    protected final Headers headers;
    /**
     * HTTP method
     */
    protected String method;
    /**
     * 该响应对应的请求
     */
    protected Request request;

    protected AbstractResponse(String url, int statusCode, List<Cookie> cookies, Headers headers, Request request) {
        this.url = url;
        this.statusCode = statusCode;
        this.cookies = Collections.unmodifiableList(cookies);
        this.headers = headers;
        this.request = request;
    }

    /**
     * return actual url (redirected)
     */
    public String url() {
        return url;
    }

    /**
     * http method
     * @return
     */
    public String method() {
        return method;
    }
    /**
     * return response status code
     *
     * @return status code
     */
    public int statusCode() {
        return statusCode;
    }

    /**
     * Get all cookies returned by this response
     */

    public List<Cookie> cookies() {
        return cookies;
    }

    /**
     * Return all response headers
     */
    public List<Header> headers() {
        return headers.getHeaders();
    }

    /**
     * Get first cookie match the name returned by this response, return null if not found
     */
    @Nullable
    public Cookie getCookie(String name) {
        for (Cookie cookie : cookies) {
            if (cookie.name().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * Get first header value match the name, return null if not exists
     */
    @Nullable
    public String getHeader(String name) {
        return headers.getHeader(name);
    }

    /**
     * Get all headers values with name. If not exists, return empty list
     */
    public List<String> getHeaders(String name) {
        return this.headers.getHeaders(name);
    }


}
