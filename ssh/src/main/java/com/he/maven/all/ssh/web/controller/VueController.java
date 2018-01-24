package com.he.maven.all.ssh.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by heyanjing on 2017/12/16 14:29.
 */
@Controller
@RequestMapping("/vue")
public class VueController {
    private static final Logger log = LoggerFactory.getLogger(VueController.class);


    /**
     * @http /vue/index
     */
    @GetMapping(value = {"/index", "/index/"})
    public String vue() {
        return "/vue/vue_test";
    }
}
