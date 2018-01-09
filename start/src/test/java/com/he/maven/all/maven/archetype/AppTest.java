package com.he.maven.all.maven.archetype;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by heyanjing on 2018/1/8 9:31.
 */
public class AppTest {
    private static final Logger log = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void test() throws Exception {
        log.debug("AppTest");
        log.info("AppTest");
        log.warn("AppTest");
        log.error("AppTest");
        App.test();
    }

}