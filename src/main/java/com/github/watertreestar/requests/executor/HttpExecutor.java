package com.github.watertreestar.requests.executor;

import com.github.watertreestar.requests.Request;
import com.github.watertreestar.requests.Response;
import com.github.watertreestar.requests.exception.RequestsException;

public interface HttpExecutor {
    Response proceed(Request request) throws RequestsException;
}
