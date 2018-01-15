package com.he.maven.all.ssh.base.core.vcode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by heyanjing on 2018/1/13 22:21.
 */
public class SpecCaptchaTest {
    private static final Logger log = LoggerFactory.getLogger(SpecCaptchaTest.class);
    @Test
    public void out() throws Exception {
        for (int i = 0; i < 100; i++) {
            File file = new File("D:\\ide\\temp\\original", i + ".png");
            SpecCaptcha captcha = new SpecCaptcha(146, 34, 4);
            captcha.out(new FileOutputStream(file));
            log.info(captcha.text());
        }

    }

}