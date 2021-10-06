package com.github.watertreestar.requests.factory;

import com.github.watertreestar.requests.executor.HttpExecutor;
import com.github.watertreestar.requests.executor.UrlConnectionHttpExecutor;

public class RequestsFactory {
    public HttpExecutor httpExecutor() {
        return new UrlConnectionHttpExecutor();
    }
}
