package com.github.watertreestar.requests;

import com.github.watertreestar.requests.constant.HttpHeaders;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Headers implements Serializable {
    private final List<Header> headers;

    public Headers() {
        headers = new ArrayList<>();
    }

    public Headers(List<Header> headers) {
        this.headers = headers;
    }

    public List<String> getHeaders(String name) {
        return this.headers.stream().filter(header -> header.getKey().equals(name))
                .map(Header::getValue).collect(Collectors.toList());
    }

    public String getHeader(String name) {
        List<String> headers = this.getHeaders(name);
        return headers.size() > 0 ? headers.get(0) : null;
    }

    public List<Header> getHeaders() {
        return headers;
    }


    public Charset getCharset(Charset defaultCharset) {
        String contentType = this.getHeader(HttpHeaders.NAME_CONTENT_TYPE);
        if (contentType == null) {
            return defaultCharset;
        }
        String[] items = contentType.split(";");
        for (String item : items) {
            item = item.trim();
            if (item.isEmpty()) {
                continue;
            }
            int idx = item.indexOf('=');
            if (idx < 0) {
                continue;
            }
            String key = item.substring(0, idx).trim();
            if (key.equalsIgnoreCase("charset")) {
                try {
                    return Charset.forName(item.substring(idx + 1).trim());
                } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
                    return defaultCharset;
                }
            }
        }
        return defaultCharset;
    }
}
