package com.he.maven.all.ssh.base.core.vcode.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by heyanjing on 2018/1/13 22:27.
 */
public class Ocr {
    private static final Logger log = LoggerFactory.getLogger(Ocr.class);

    /**
     * @param tessdataPath 训练语言根路径
     * @param lange 语言
     * @param imgPath 图片路径
     * @return
     */
    public static String recognize(String tessdataPath, String lange, String imgPath) throws TesseractException {
        ITesseract instance = new Tesseract();
        instance.setDatapath(tessdataPath);
        instance.setLanguage(lange);
        File imgDir = new File(imgPath);
        String result = instance.doOCR(imgDir);
        return result;
    }
}
