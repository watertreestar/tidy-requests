package com.github.watertreestar.requests.session;

import com.github.watertreestar.requests.Cookie;

import java.util.ArrayList;
import java.util.List;

public class NoopSessionContext implements SessionContext{
    @Override
    public List<Cookie> cookies() {
        return new ArrayList<>();
    }

    @Override
    public void storeCookie(List<Cookie> cookies) {
    }
}
