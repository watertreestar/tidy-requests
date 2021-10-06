package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;

import java.net.URL;
import java.util.List;

/**
 * 存储http请求和响应过程中的cookie
 */
public interface CookieStore {
    void storeCookies(List<Cookie> cookies);

    void storeCookie(Cookie cookie);

    List<Cookie> getCookies(URL url);

    List<Cookie> getCookies(String url);

    List<Cookie> allCookies();
}
