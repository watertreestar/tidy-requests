package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;
import com.github.watertreestar.requests.RequestBuilder;
import com.github.watertreestar.requests.constant.HttpMethod;

import java.net.URL;
import java.util.List;

public class Session {
    private final SessionContext sessionContext;

    public Session(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public RequestBuilder get(URL url) {
        return newRequest(HttpMethod.GET, url);
    }

    public RequestBuilder post(URL url) {
        return newRequest(HttpMethod.POST, url);
    }

    public RequestBuilder put(URL url) {
        return newRequest(HttpMethod.PUT, url);
    }

    public RequestBuilder head(URL url) {
        return newRequest(HttpMethod.HEAD, url);
    }

    public RequestBuilder delete(URL url) {
        return newRequest(HttpMethod.DELETE, url);
    }

    public RequestBuilder patch(URL url) {
        return newRequest(HttpMethod.PATCH, url);
    }

    /**
     * Return all cookies this session current hold.
     */
    public List<Cookie> currentCookies() {
        return sessionContext.cookies();
    }

    /**
     * Create new request with method and url
     */
    public RequestBuilder newRequest(HttpMethod method, URL url) {
        return new RequestBuilder().sessionContext(sessionContext).url(url).method(method);
    }

}
