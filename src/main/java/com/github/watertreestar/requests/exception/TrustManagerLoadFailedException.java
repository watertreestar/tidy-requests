package com.github.watertreestar.requests.exception;

public class TrustManagerLoadFailedException extends RequestsException {

    public TrustManagerLoadFailedException(Exception e) {
        super(e);
    }

    public TrustManagerLoadFailedException(String message) {
        super(message);
    }
}
