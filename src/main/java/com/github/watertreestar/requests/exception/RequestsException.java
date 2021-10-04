package com.github.watertreestar.requests.exception;

public class RequestsException extends RuntimeException {
    public RequestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestsException(Throwable e) {
        super(e);
    }
}
