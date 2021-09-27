package com.github.watertreestar.requests;

import java.io.Serializable;
import java.util.List;

/**
 * HTTP response
 * <p>该response是经过转换后的response</p>
 * @param <T>
 */
public class ParsedResponse<T> extends AbstractResponse implements Serializable {
    private T body;

    protected ParsedResponse(String url, int statusCode, List<Cookie> cookies, Headers headers) {
        super(url, statusCode, cookies, headers);
    }

    public T body() {
        return body;
    }
}
