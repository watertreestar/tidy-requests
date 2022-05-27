package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;

import java.net.URL;
import java.util.List;

public class DefaultSessionContext implements SessionContext {
    private final CookieStore cookieStore;

    public DefaultSessionContext(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public DefaultSessionContext() {
        this.cookieStore = new DefaultCookieStore();
    }

    public List<Cookie> cookies() {
        return cookieStore.allCookies();
    }

    public List<Cookie> cookies(URL url) {
        return cookieStore.getCookies(url);
    }


    @Override
    public void storeCookie(List<Cookie> cookies) {
        cookieStore.storeCookies(cookies);
    }
}
