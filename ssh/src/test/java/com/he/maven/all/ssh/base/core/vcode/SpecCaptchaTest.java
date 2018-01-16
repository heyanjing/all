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
        for (int i = 0; i < 200; i++) {
            File file = new File("C:\\temp\\original", i + ".png");
            SpecCaptcha captcha = new SpecCaptcha(360, 34, 10);
            captcha.out(new FileOutputStream(file));
            log.info(captcha.text());
        }
    }
    @Test
    public void out2() throws Exception {
        for (int i = 0; i < 100; i++) {
            File file = new File("C:\\temp\\originalx", i + ".png");
            SpecCaptcha captcha = new SpecCaptcha(160, 34, 4);
            captcha.out(new FileOutputStream(file));
            log.info(captcha.text());
        }
    }
}