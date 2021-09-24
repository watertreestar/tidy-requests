package com.github.watertreestar.requests;

import java.util.Objects;

/**
 * Represent a http header
 */
public class Header extends Parameter<String>{
    public Header(String name, String value) {
        super(name, value);
    }

    public Header(String name, Object value) {
        super(name, Objects.requireNonNull(value).toString());
    }

    public static Header of(String name, String value) {
        return new Header(name,value);
    }

    public static Header of(String name, Object value) {
        return new Header(name, value);
    }
}
