package com.he.maven.all.ssh.base.core.async;

import com.he.maven.all.ssh.base.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import java.util.concurrent.Callable;

/**
 * Created by heyanjing on 2017/11/27 16:24.
 */
@Slf4j
public class MyCallableInterceptor implements CallableProcessingInterceptor {
    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        log.info("beforeConcurrentHandling1111111111");
    }

    @Override
    public <T> void preProcess(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        log.info("preProcess222222222");
    }

    @Override
    public <T> void postProcess(NativeWebRequest nativeWebRequest, Callable<T> callable, Object o) throws Exception {
        log.info("postProcess3333333");
        Result r = (Result) o;
        r.setMsg("成功可以改，超时不能改");
    }

    @Override
    public <T> Object handleTimeout(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        log.info("handleTimeout444444444");
//        HttpServletResponse servletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
//        if (!servletResponse.isCommitted()) {
//            servletResponse.setContentType("text/plain;charset=utf-8");
//            servletResponse.getWriter().write("超时了");
//            servletResponse.getWriter().close();
//        }
        return Result.failure("请求超时");
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest nativeWebRequest, Callable<T> callable) throws Exception {
        log.info("afterCompletion55555555");
    }
}
