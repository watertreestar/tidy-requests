package com.github.watertreestar.requests;

import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BasicAuth implements Serializable {
    private final String user;
    private final String password;

    public BasicAuth(String user, String password) {
        this.user = Objects.requireNonNull(user);
        this.password = Objects.requireNonNull(password);
    }

    public String user() {
        return user;
    }

    public String password() {
        return password;
    }

    /**
     * Encode to http header
     */
    public String encode() {
        return "Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes(UTF_8));
    }
}
