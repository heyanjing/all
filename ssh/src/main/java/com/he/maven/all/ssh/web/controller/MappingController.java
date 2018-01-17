package com.he.maven.all.ssh.web.controller;

import com.he.maven.all.ssh.base.core.Guava;
import com.he.maven.all.ssh.other.SpringHelper;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by heyanjing on 2018/1/17 13:28.
 */
@Controller
public class MappingController {
    private static final Logger log = LoggerFactory.getLogger(MappingController.class);
    /**
     * 需要注意下面几点：
     * （1）RequestMappingHandlerMapping这个类在程序中用@Autowired来自动装配得到对象，但实际上，spring容器是没有注入到这个bean的，这个可以用applicationContext.getBean("requestMappingHandlerMapping")方法来验证得到。
     * （2）RequestMappingHandlerMapping这个类的对象，只能在controller层用，并且要在申明了@RequestMapping的方法里面用，从RequestMappingHandlerMapping的源码注释里面可以看出：
     * （3）其它一般用@Autowired来自动装配得到对象的bean，前提是要在spring容器里面注入，这个RequestMappingHandlerMapping不用注入，估计是spring里springMVC自己的机制引起的。
     */
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SpringHelper springHelper;

    @RequestMapping(value = "/mappings2")
    @ResponseBody
    public Object getUrlMapping(HttpServletRequest request) {
        //WebApplicationContext wc = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        //RequestMappingHandlerMapping rmhp = wc.getBean(RequestMappingHandlerMapping.class);
        List<String> result = Guava.newArrayList();
        RequestMappingHandlerMapping rmhp = requestMappingHandlerMapping;
        Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            log.info(info.getPatternsCondition().toString() + "," + map.get(info).getBean().toString());
            result.add(info.getPatternsCondition().toString() + "," + map.get(info).getBean().toString());
        }
        return result;
    }

    @RequestMapping(value = "/mappings")
    @ResponseBody
    public Object list(Model model) {
        log.info("{}", applicationContext);
        log.info("{}", requestMappingHandlerMapping);
        List<HashMap<String, String>> urlList = new ArrayList<HashMap<String, String>>();

        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HashMap<String, String> hashMap = new HashMap<>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                hashMap.put("url", url);
            }
            hashMap.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            hashMap.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            String type = methodsCondition.toString();
            if (type != null && type.startsWith("[") && type.endsWith("]")) {
                type = type.substring(1, type.length() - 1);
                hashMap.put("type", type); // 方法名
            }
            urlList.add(hashMap);
        }
        model.addAttribute("list", urlList);
        return urlList;
    }

    /**
     * @return
     * @author Elwin ZHANG
     * 创建时间：2017年3月8日 上午11:48:22
     * 功能：返回系统中的所有控制器映射路径，以及对应的方法
     */
    @RequestMapping(value = "/mappings3", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getMapPaths() {
        String result = "";
        //RequestMappingHandlerMapping rmhp = springHelper.getObject(RequestMappingHandlerMapping.class);
        RequestMappingHandlerMapping rmhp = requestMappingHandlerMapping;
        Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            result += info.getPatternsCondition().toString().replace("[", "").replace("]", "") + "\t";
            HandlerMethod hm = map.get(info);
            result += hm.getBeanType().getName() + "\t";
            result += getMethodParams(hm.getBeanType().getName(), hm.getMethod().getName()) + "\t";
            result += info.getProducesCondition().toString().replace("[", "").replace("]", "") + "\t";
            result += "\r\n";
        }
        return result;
    }

    /**
     * @param className  类名
     * @param methodName 方法名
     * @return 该方法的声明部分
     * @author Elwin ZHANG
     * 创建时间：2017年3月8日 上午11:47:16
     * 功能：返回一个方法的声明部分，包括参数类型和参数名
     */
    private String getMethodParams(String className, String methodName) {
        String result = "";
        try {
            ClassPool pool = ClassPool.getDefault();
            ClassClassPath classPath = new ClassClassPath(this.getClass());
            pool.insertClassPath(classPath);
            CtMethod cm = pool.getMethod(className, methodName);
            // 使用javaassist的反射方法获取方法的参数名
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            result = cm.getName() + "(";
            if (attr == null) {
                return result + ")";
            }
            CtClass[] pTypes = cm.getParameterTypes();
            String[] paramNames = new String[pTypes.length];
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < paramNames.length; i++) {
                if (!pTypes[i].getSimpleName().startsWith("HttpServletRe")) {
                    result += pTypes[i].getSimpleName();
                    paramNames[i] = attr.variableName(i + pos);
                    result += " " + paramNames[i] + ",";
                }
            }
            if (result.endsWith(",")) {
                result = result.substring(0, result.length() - 1);
            }
            result += ")";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "mappings4", method = RequestMethod.GET)
    @ResponseBody
    public Object getendpointsinview(Model model) {
        List<Map<String, Object>> result = Guava.newArrayList();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        Set mappings = new TreeSet<String>();
        for (RequestMappingInfo info : map.keySet()) {
            Map<String, Object> temp = Guava.newHashMap();
            HandlerMethod method = map.get(info);
            // package
            String klassname = method.getMethod().getDeclaringClass().getCanonicalName();
            temp.put("klassname", klassname);
            // class
            String simpleName = method.getMethod().getDeclaringClass().getSimpleName();
            temp.put("simpleName", simpleName);
            // method
            String name = method.getMethod().getName();
            temp.put("name", name);
            // verb
            String verb = StringUtils.strip(info.getMethodsCondition().toString(), "[]");
            if ("".equals(verb)) {
                verb = "get";
            }
            temp.put("verb", verb);
            // url
            String url = StringUtils.strip(info.getPatternsCondition().toString(), "[]");
            temp.put("url", url);
            result.add(temp);
        }
        return result;
    }
}
