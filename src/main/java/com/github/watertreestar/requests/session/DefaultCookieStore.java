package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;
import com.github.watertreestar.requests.util.CookieUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCookieStore implements CookieStore {
    private final Map<Cookie.Key, Cookie> cookieMap = new HashMap<>();

    @Override
    public void storeCookies(List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.key(), cookie);
        }
        removeExpiredCookies();
    }

    private void removeExpiredCookies() {
        long now = System.currentTimeMillis();
        cookieMap.entrySet().removeIf(entry -> entry.getValue().expired(now));
    }


    @Override
    public void storeCookie(Cookie cookie) {
        cookieMap.put(cookie.key(), cookie);
    }

    @Override
    public List<Cookie> getCookies(URL url) {
        long now = System.currentTimeMillis();
        List<Cookie> matched = new ArrayList<>();
        for (Cookie cookie : cookieMap.values()) {
            if (!CookieUtil.match(cookie, url.getProtocol(), url.getHost().toLowerCase(), url.getPath())) {
                continue;
            }
            if (cookie.expired(now)) {
                continue;
            }
            matched.add(cookie);
        }
        // we did not sort using create time here
        matched.sort((cookie1, cookie2) -> cookie2.path().length() - cookie1.path().length());
        return matched;
    }

    @Override
    public List<Cookie> getCookies(String url) throws MalformedURLException {
        return getCookies(new URL(url));
    }

    @Override
    public List<Cookie> allCookies() {
        return new ArrayList<>(cookieMap.values());
    }
}
