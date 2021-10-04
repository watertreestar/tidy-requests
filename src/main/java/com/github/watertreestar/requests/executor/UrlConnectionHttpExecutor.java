package com.github.watertreestar.requests.executor;

import com.github.watertreestar.requests.Request;
import com.github.watertreestar.requests.Response;
import com.github.watertreestar.requests.exception.RequestsException;
import com.github.watertreestar.requests.util.URLUtil;

import java.net.URL;
import java.nio.charset.Charset;

/**
 * 基于JDK UrlConnection实现Http请求
 */
public class UrlConnectionHttpExecutor implements HttpExecutor{
    @Override
    public Response proceed(Request request) throws RequestsException {
        // 1. 发起请求
        Response response = this.doRequest(request);

        // 2. 处理响应 - 错误处理，重定向处理
        return response;
    }

    private Response doRequest(Request request) {
        // build request url
        Charset charset = request.charset();
        URL fullUrl = URLUtil.concatUrl(request.url(), request.charset(), URLUtil.entryToParameter(request.parameters()));
        return null;
    }

}
