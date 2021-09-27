package com.github.watertreestar.requests.constant;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    HEAD("HEAD"),
    DELETE("DELETE"),
    PATCH("PATCH"),
    TRACE("TRACE"),
    OPTIONS("OPTIONS"),
    CONNECT("CONNECT");

    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private final String name;
}
