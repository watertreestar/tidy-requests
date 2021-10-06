package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCookieStore implements CookieStore{
    private Map<String,Cookie> cookieMap = new HashMap<>();

    @Override
    public void storeCookies(List<Cookie> cookies) {

    }

    @Override
    public void storeCookie(Cookie cookie) {

    }

    @Override
    public List<Cookie> getCookies(URL url) {
        return null;
    }

    @Override
    public List<Cookie> getCookies(String url) {
        return null;
    }

    @Override
    public List<Cookie> allCookies() {
        return null;
    }
}
