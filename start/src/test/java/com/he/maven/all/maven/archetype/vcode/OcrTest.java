package com.he.maven.all.maven.archetype.vcode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by heyanjing on 2018/1/13 22:39.
 */
public class OcrTest {
    private static final Logger log = LoggerFactory.getLogger(OcrTest.class);

    /**
     * SpecCaptchaTest.out
     * ImageDenoiseTest.cleanImage
     */
    @Test
    public void recognize() throws Exception {
        String tessdataPath = "D:\\opt\\Tess4J\\tessdata";
        String lange = "eng";
        lange = "chi_sim";
        for (int i = 0; i < 50; i++) {
            String imgPath = "D:\\ide\\temp\\original\\" + i + ".png";
            imgPath = "D:\\ide\\temp\\denoise\\" + i + ".png";
            String result = Ocr.recognize(tessdataPath, lange, imgPath);
            log.info(result.replaceAll("\\n", ""));
        }
    }



}