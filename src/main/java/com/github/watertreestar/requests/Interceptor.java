package com.github.watertreestar.requests;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface Interceptor {

    Response intercept(InvocationTarget target, Request request);

    interface InvocationTarget {
        /**
         * Process the request, and return response
         */
        @NonNull
        Response proceed(Request request);
    }
}
