package com.he.maven.all.ssh.base.core.async;

import com.alibaba.fastjson.JSON;
import com.he.maven.all.ssh.base.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by heyanjing on 2017/11/29 9:46.
 */
@Slf4j
public class MyDeferredResultInterceptor implements DeferredResultProcessingInterceptor {
    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
        log.info("beforeConcurrentHandling111111111");
    }

    @Override
    public <T> void preProcess(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
        log.info("preProcess22222222");
    }

    @Override
    public <T> void postProcess(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult, Object o) throws Exception {
        log.info("postProcess3333333");
        Result r= (Result) o;
        r.setMsg("改了");
    }

    @Override
    public <T> boolean handleTimeout(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
        log.info("handleTimeout4444444");
//        HttpServletResponse servletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
//        if (!servletResponse.isCommitted()) {
//            servletResponse.setContentType("text/plain;charset=utf-8");
//            servletResponse.getWriter().write("超时了");
//            servletResponse.getWriter().close();
//        }
        return false;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest nativeWebRequest, DeferredResult<T> deferredResult) throws Exception {
        log.info("afterCompletion555555");
        HttpServletResponse servletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        if (!servletResponse.isCommitted()) {
            servletResponse.setContentType("text/plain;charset=utf-8");
            servletResponse.getWriter().write(JSON.toJSONString(Result.success("完成了")));
            servletResponse.getWriter().close();
        }
    }

    @Override
    public <T> boolean handleError(NativeWebRequest request, DeferredResult<T> deferredResult, Throwable t) throws Exception {
        return false;
    }
}
