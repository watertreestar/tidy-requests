package com.github.watertreestar.requests;

import com.github.watertreestar.io.Readers;
import com.github.watertreestar.requests.exception.RequestsException;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Response extends AbstractResponse implements AutoCloseable {
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


    public Response(String method, String url, int statusCode, String statusLine, List<Cookie> cookies,
                    Headers headers, InputStream body,  Request request) {
        super(url, statusCode, cookies, headers, request);
        this.method = method;
        this.statusLine = statusLine;
        this.body = body;
        this.charset = null;
        this.decompress = request.useCompress();
    }

    public InputStream body() {
        return this.body;
    }

    @Override
    public void close() {
        if (body != null) {
            try {
                body.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    /**
     * 从response stream中读取出文本
     * @return
     */
    public String readToText() {
        Charset charset = getCharset();
        try (InputStream in = body();
             Reader reader = new InputStreamReader(in, charset)) {
            return Readers.readAll(reader);
        } catch (IOException e) {
            throw new RequestsException(e);
        } finally {
            close();
        }
    }

    public ParsedResponse<String> toTextResponse() {
        return new ParsedResponse<>(this.url, this.statusCode, this.cookies, this.headers, this.readToText(), request);
    }

    /**
     * 从response stream中读取出bytes
     * @return
     */
    public byte[] readToBytes() {
        return null;
    }

    /**
     * convert response to byte array
     * @return
     */
    public ParsedResponse<byte[]> toByteResponse() {
        return new ParsedResponse<>(this.url, this.statusCode, this.cookies, this.headers, this.readToBytes(), request);
    }

    private Charset getCharset() {
        if (this.charset != null) {
            return this.charset;
        }
        return headers.getCharset(UTF_8);
    }
}
