package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;

import java.util.List;

public class DefaultSessionContext implements SessionContext{
    private final CookieStore cookieStore;
    private final String url;

    public DefaultSessionContext(CookieStore cookieStore, String url) {
        this.cookieStore = cookieStore;
        this.url = url;
    }

    @Override
    public List<Cookie> cookies() {
        return cookieStore.getCookies(url);
    }

    @Override
    public void storeCookie(List<Cookie> cookies) {
        cookieStore.storeCookies(cookies);
    }
}
