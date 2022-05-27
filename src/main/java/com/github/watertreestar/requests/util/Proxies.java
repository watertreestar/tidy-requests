package com.github.watertreestar.requests.util;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * 用来生成HTTP connection可用的Proxy
 */
public class Proxies {
    public static Proxy httpProxy(String host, int port) {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
    }

    public static Proxy socksProxy(String host, int port) {
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, port));
    }
}
