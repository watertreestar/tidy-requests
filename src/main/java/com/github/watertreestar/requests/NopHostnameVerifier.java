package com.github.watertreestar.requests;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class NopHostnameVerifier implements HostnameVerifier {

    private static class Holder {
        private static NopHostnameVerifier instance = new NopHostnameVerifier();
    }

    public static HostnameVerifier getInstance() {
        return Holder.instance;
    }

    @Override
    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }
}
