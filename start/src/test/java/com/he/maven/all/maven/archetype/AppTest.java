package com.he.maven.all.maven.archetype;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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

    /**
     * https://www.jianshu.com/p/c905d7f8de8e
     * Tesseract-OCR支持中文识别，并且开源和提供全套的训练工具，是快速低成本开发的首选。而Tess4J则是Tesseract在Java PC上的应用。在英文和数字识别中性能还是不错的，但是在中文识别中，无论速度还是识别率还是较弱，建议有条件的话，针对场景进行训练，会获得较好结果，本文仅对目前Tess4J的用法进行介绍。
     * 没有干扰的图片能识别出来
     */
    @Test
    public void t() throws Exception {
        ITesseract instance = new Tesseract();
        //如果未将tessdata放在根目录下需要指定绝对路径
        instance.setDatapath("C:\\temp\\Tess4J\\tessdata");
        // 我们需要指定识别语种
        instance.setLanguage("chi_sim");
        //instance.setLanguage("eng");
        // 指定识别图片
        File imgDir = new File("C:\\temp\\xw.png");
        long startTime = System.currentTimeMillis();
        String ocrResult = instance.doOCR(imgDir);
        // 输出识别结果
        System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");

    }
}