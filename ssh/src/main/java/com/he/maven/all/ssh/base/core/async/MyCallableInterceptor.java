package com.he.maven.all.ssh.base.core.async;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

/**
 * Created by heyanjing on 2017/11/27 16:24.
 */
public class MyCallableInterceptor implements CallableProcessingInterceptor {
    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {

    }

    @Override
    public <T> void preProcess(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {

    }

    @Override
    public <T> void postProcess(NativeWebRequest nativeWebRequest, Callable<T> callable, Object o) throws Exception {

    }

    @Override
    public <T> Object handleTimeout(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        HttpServletResponse servletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        if (!servletResponse.isCommitted()) {
            servletResponse.setContentType("text/plain;charset=utf-8");
            servletResponse.getWriter().write("超时了");
            servletResponse.getWriter().close();
        }
        return null;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {

    }
}
