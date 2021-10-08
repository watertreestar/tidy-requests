package com.github.watertreestar.requests;

import com.github.watertreestar.CollectionUtil;
import com.github.watertreestar.requests.executor.HttpExecutor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class InterceptorChain implements Interceptor.InvocationTarget {
    private HttpExecutor executor;

    private List<Interceptor> interceptorList;


    public InterceptorChain(List<Interceptor> interceptorList, HttpExecutor executor) {
        this.executor = executor;
        this.interceptorList = interceptorList;
    }


    @Override
    public @NonNull Response proceed(Request request) {
        if(CollectionUtil.isEmpty(this.interceptorList)) {
            return this.executor.proceed(request);
        }
        Interceptor interceptor = interceptorList.get(0);
        InterceptorChain chain = new InterceptorChain(interceptorList.subList(1, interceptorList.size()), executor);
        return interceptor.intercept(chain, request);
    }
}
