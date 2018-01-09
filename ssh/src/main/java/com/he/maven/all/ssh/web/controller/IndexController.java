package com.he.maven.all.ssh.web.controller;

import com.he.maven.all.ssh.base.bean.Result;
import com.he.maven.all.ssh.base.bean.Results;
import com.he.maven.all.ssh.other.PersonList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2017/12/16 14:29.
 */
@Controller
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = {"", "/"})
    public String index() {
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        log.warn("{}", servletContext);
        return "/index";
    }

    @GetMapping(value = {"/admin", "/admin/"})
    public String admin() {
        return "/index";
    }

    @GetMapping(value = {"/user", "/user/"})
    public String user() {
        return "/index";
    }

    @GetMapping(value = {"/other", "/other/"})
    public String other() {
        return "/index";
    }

    @GetMapping(value = {"/javadoc", "/javadoc/"})
    public String javadoc() {
        return "/index";
    }

    /**
     * http://localhost:8080/ssh/list?params=a&params=b&params=c
     *
     * @param params params
     *               var arr = [];
     *               arr.push("a");
     *               arr.push("b");
     *               arr.push("c");
     *               $.ajax({
     *               type: 'post',
     *               dataType: 'json',
     *               url: CTX + "/list",
     *               data: {
     *               params: arr
     *               },
     *               success: function (result) {
     *               },
     *               error: function () {
     *               }
     *               });
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestParam("params[]") List<String> params) {
        Result result = Results.success();
        log.info("{}", params);
        result.setData(params);
        return result;
    }

    /**
     * http://localhost:8080/ssh/persons?persons[0].name=name0&persons[0].age=0&persons[1].name=name1&persons[1].age=1
     *
     * @param persons persons
     */
    @RequestMapping("/persons")
    @ResponseBody
    public Result users(PersonList persons) {
        Result result = Results.success();
        log.info("{}", persons);
        result.setData(persons);
        return result;
    }

    /**
     * http://localhost:8080/ssh/map?param1=a&param2=b&param3=c
     *
     * @param map map
     *            var map = {};
     *            map.a = "a";
     *            map.b = "b";
     *            map.c = "c";
     *            $.ajax({
     *            type: 'post',
     *            dataType: 'json',
     *            url: CTX + "/map",
     *            data: map,
     *            success: function (result) {
     *            },
     *            error: function () {
     *            }
     *            });
     */
    @RequestMapping("/map")
    @ResponseBody
    public Result map(@RequestParam Map<String, Object> map) {
        Result result = Results.success();
        log.info("{}", map);
        result.setData(map);
        return result;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Result test() {
        Result result = Results.success();
        return result;
    }
}
