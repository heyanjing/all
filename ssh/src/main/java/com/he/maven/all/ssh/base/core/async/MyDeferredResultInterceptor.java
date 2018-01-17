package com.he.maven.all.ssh.base.core.async;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by heyanjing on 2017/11/29 9:46.
 */
public class MyDeferredResultInterceptor implements DeferredResultProcessingInterceptor {
    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {

    }

    @Override
    public <T> void preProcess(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {

    }

    @Override
    public <T> void postProcess(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult, Object o) throws Exception {

    }

    @Override
    public <T> boolean handleTimeout(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
        HttpServletResponse servletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        if (!servletResponse.isCommitted()) {
            servletResponse.setContentType("text/plain;charset=utf-8");
            servletResponse.getWriter().write("超时了");
            servletResponse.getWriter().close();
        }
        return false;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {

    }
}
