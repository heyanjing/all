package com.he.maven.all.ssh.web.controller;

import com.he.maven.all.ssh.base.core.img.Img;
import com.he.maven.all.ssh.base.core.vcode.Captcha;
import com.he.maven.all.ssh.base.core.vcode.GifCaptcha;
import com.he.maven.all.ssh.base.core.vcode.SpecCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/code")
public class CoderController {
    private static final Logger log = LoggerFactory.getLogger(CoderController.class);
    private static final String attrCode = "_code";

    /**
     * http /code/base64Img
     */
    @RequestMapping(value = "/base64Img")
    @ResponseBody
    public List<String> base64Img(String base64Img) {
        List<String> imgs = null;
        try {
            imgs = Img.base64ToImg(base64Img, "C:\\temp\\img");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgs;
    }

    /**
     * http /code/gif
     */
    @RequestMapping(value = "/gif", method = RequestMethod.GET)
    public void gif(HttpServletResponse response, HttpServletRequest request) {
        generateCode(response, request, new GifCaptcha(146, 33, 4));
    }

    /**
     * http /code/jpg
     */
    @RequestMapping(value = "/jpg", method = RequestMethod.GET)
    public void jpg(HttpServletResponse response, HttpServletRequest request) {
        generateCode(response, request, new SpecCaptcha(146, 33, 4));
    }

    private void generateCode(HttpServletResponse response, HttpServletRequest request, Captcha captcha) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");
            captcha.out(response.getOutputStream());

            HttpSession session = request.getSession();
            session.setAttribute("_code", captcha.text().toLowerCase());
            log.warn(captcha.text().toLowerCase());
            log.warn("{}", session.getAttribute("_code"));
        } catch (Exception e) {
            log.error("{}", e);
        }
    }


}
