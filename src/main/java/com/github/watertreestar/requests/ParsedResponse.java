package com.github.watertreestar.requests;

import java.io.*;
import java.util.List;

/**
 * Parsed response from {@code Response}
 * <p>该response是经过转换后的response. {@link Response}只是包含了响应流，
 * ParsedResponse是从response stream中根据不同的content-type convert而来
 * @param <T>
 */
public class ParsedResponse<T> extends AbstractResponse implements Serializable {
    private T body;

    protected ParsedResponse(String url, int statusCode, List<Cookie> cookies, Headers headers, Request request) {
        super(url, statusCode, cookies, headers, request);
    }

    public ParsedResponse(String url, int statusCode, List<Cookie> cookies, Headers headers, T body, Request request) {
        this(url, statusCode, cookies, headers, request);
        this.body = body;
    }

    public T body() {
        return body;
    }

}
