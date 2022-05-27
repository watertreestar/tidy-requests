package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;

import java.net.URL;
import java.util.List;

public interface SessionContext {
    List<Cookie> cookies();

    List<Cookie> cookies(URL url);

    void storeCookie(List<Cookie> cookies);
}
