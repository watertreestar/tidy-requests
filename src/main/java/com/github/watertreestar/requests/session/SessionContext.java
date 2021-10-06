package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;

import java.util.List;

public interface SessionContext {
    List<Cookie> cookies();

    void storeCookie(List<Cookie> cookies);
}
