package com.github.watertreestar.requests.constant;

public enum HttpMethods {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    HEAD("HEAD"),
    DELETE("DELETE"),
    PATCH("PATCH"),
    TRACE("TRACE"),
    OPTIONS("OPTIONS"),
    CONNECT("CONNECT");

    HttpMethods(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private final String name;
}
