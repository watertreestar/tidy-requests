package com.github.watertreestar.requests;

import com.github.watertreestar.requests.constant.HttpMethod;

public class Requests {
    public static RequestBuilder get(String url) {
        return buildRequest(url, HttpMethod.GET);
    }

    public static RequestBuilder post(String url){
        return buildRequest(url, HttpMethod.POST);
    }

    public static RequestBuilder patch(String url){
        return buildRequest(url, HttpMethod.PATCH);
    }

    public static RequestBuilder put(String url){
        return buildRequest(url, HttpMethod.PUT);
    }

    public static RequestBuilder delete(String url){
        return buildRequest(url, HttpMethod.DELETE);
    }

    public static RequestBuilder tract(String url){
        return buildRequest(url, HttpMethod.TRACE);
    }

    public static RequestBuilder connect(String url){
        return buildRequest(url, HttpMethod.CONNECT);
    }

    public static RequestBuilder options(String url){
        return buildRequest(url, HttpMethod.OPTIONS);
    }

    public static RequestBuilder head(String url){
        return buildRequest(url, HttpMethod.HEAD);
    }

    private static RequestBuilder buildRequest(String url, HttpMethod method) {
        return new RequestBuilder().url(url).method(method);
    }
}
