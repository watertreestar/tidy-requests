package com.github.watertreestar.requests;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

public class Response extends AbstractResponse implements Closeable {
    /**
     * HTTP method
     */
    private final String method;
    /**
     * 响应行
     */
    private final String statusLine;
    /**
     * 响应body
     */
    private final InputStream body;
    /**
     * 响应body
     */
    @Nullable
    private final Charset charset;
    /**
     * 是否需要解码
     */
    private final boolean decompress;
    /**
     * 该响应对应的请求
     */
    private final Request request;

    public Response(String method, String url, int statusCode, String statusLine, List<Cookie> cookies,
                    Headers headers, InputStream body, @Nullable Charset charset, boolean decompress, Request request) {
        super(url, statusCode, cookies, headers);
        this.method = method;
        this.statusLine = statusLine;
        this.body = body;
        this.charset = charset;
        this.decompress = decompress;
        this.request = request;
    }

    @Override
    public void close() throws IOException {
        if (body != null) {
            body.close();
        }
    }
}
