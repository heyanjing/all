package com.he.maven.all.ssh.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by heyanjing on 2017/12/18 11:20.
 */
@Component
public class SpringHelper implements ApplicationContextAware, ServletContextAware {
    private static final Logger log = LoggerFactory.getLogger(SpringHelper.class);
    private static ApplicationContext ac;
    private static ServletContext sc;

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        SpringHelper.ac = ac;
    }

    @Override
    public void setServletContext(ServletContext sc) {
        SpringHelper.sc = sc;
    }

    public static ApplicationContext getApplicatoinContext() {
        return ac;
    }

    public static ServletContext getServletContext() {
        return sc;
    }

    public static Object getSpringBean(String name) {
        if (ac == null) {
            throw new IllegalStateException("spring环境尚未启动！");
        }
        return ac.getBean(name);
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static String getContextPath() {
        String ctx = getRequest().getContextPath();
        return ctx.endsWith("/") ? ctx : ctx + "/";
    }


    public static String getAbsoluteUrl() {
        HttpServletRequest request = getRequest();
        return request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath();
    }

    public static String getIPAddress() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * @param beanName bean Id
     * @return 如果获取失败，则返回Null
     * @author Elwin ZHANG
     * 创建时间：2016年4月14日 上午9:52:55
     * 功能：通过BeanId获取Spring管理的对象
     */
    public Object getObject(String beanName) {
        Object object = null;
        try {
            object = ac.getBean(beanName);
        } catch (Exception e) {
            log.error("{}",e);
        }
        return object;
    }


    /**
     * @return
     * @author Elwin ZHANG
     * 创建时间：2017年3月7日 下午3:44:38
     * 功能：获取Spring的ApplicationContext
     */
    public ApplicationContext getContext() {
        return ac;
    }

    /**
     * @param clazz 要获取的Bean类
     * @return 如果获取失败，则返回Null
     * @author Elwin ZHANG
     * 创建时间：2016年4月14日 上午10:05:27
     * 功能：通过类获取Spring管理的对象
     */
    public <T> T getObject(Class<T> clazz) {
        try {
            return ac.getBean(clazz);
        } catch (Exception e) {
           log.error("{}",e);
        }
        return null;
    }

    /**
     * @param code   配置文件中消息提示的代码
     * @param locale 当前的语言环境
     * @return 当前语言对应的消息内容
     * @author Elwin ZHANG
     * 创建时间：2016年4月14日 上午10:34:25
     * 功能：获取当前语言对应的消息内容
     */
    public String getMessage(String code, Locale locale) {
        String message;
        try {
            message = ac.getMessage(code, null, locale);
        } catch (Exception e) {
           log.error("{}",e);
            message = "";
        }
        return message;
    }

    /**
     * @param code    配置文件中消息提示的代码
     * @param request 当前的HTTP请求
     * @return 当前语言对应的消息内容
     * @author Elwin ZHANG
     * 创建时间：2016年4月14日 下午3:03:37
     * 功能：获取当前语言对应的消息内容
     */
    public String getMessage(String code, HttpServletRequest request) {
        String message=null;
        try {
            //message = ac.getMessage(code, null, getCurrentLocale(request));
        } catch (Exception e) {
           log.error("{}",e);
            message = "zh_CN";
        }
        return message;
    }

    /**
     * @param request 当前的HTTP请求
     * @return 当前用户Cookie中的语言
     * @author Elwin ZHANG
     * 创建时间：2016年4月14日 下午2:59:21
     * 功能：当前用户保存Cookie中的默认语言
     */
    //public Locale getCurrentLocale(HttpServletRequest request) {
    //    return resolver.resolveLocale(request);
    //}

    ////Cookie本地语言解析器，Spring提供
    //@Autowired
    //CookieLocaleResolver resolver;


}
