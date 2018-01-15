package com.he.maven.all.maven.archetype.vcode;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyanjing on 2018/1/13 22:39.
 * https://github.com/tesseract-ocr/tesseract/wiki 可以下载tesseract的命令行工具
 * jTessBoxEditor工具下载  https://sourceforge.net/projects/vietocr/files/jTessBoxEditor/
 */
public class OcrTest {
    private static final Logger log = LoggerFactory.getLogger(OcrTest.class);
    public static List<String> orinalList=new ArrayList<>();

    @Before
    public void before() throws Exception {
        orinalList.add("Dem6");
        orinalList.add("dAma");
        orinalList.add("KnSq");
        orinalList.add("cki6");
        orinalList.add("RtWC");
        orinalList.add("9WiE");
        orinalList.add("DKDQ");
        orinalList.add("58bk");
        orinalList.add("hMkD");
        orinalList.add("E788");
        orinalList.add("YSkC");
        orinalList.add("mmWv");
        orinalList.add("qu7n");
        orinalList.add("xUFF");
        orinalList.add("4f4C");
        orinalList.add("htCq");
        orinalList.add("HXuk");
        orinalList.add("ejhk");
        orinalList.add("rze4");
        orinalList.add("EWff");
        orinalList.add("gBcN");
        orinalList.add("nihQ");
        orinalList.add("XCFc");
        orinalList.add("dfrw");
        orinalList.add("EaFN");
        orinalList.add("YPY2");
        orinalList.add("QEiA");
        orinalList.add("fhsT");
        orinalList.add("t6fb");
        orinalList.add("ejdd");
        orinalList.add("6t6A");
        orinalList.add("scBs");
        orinalList.add("maU8");
        orinalList.add("CZuY");
        orinalList.add("6a8B");
        orinalList.add("cfyf");
        orinalList.add("n4aP");
        orinalList.add("f5yQ");
        orinalList.add("Tgim");
        orinalList.add("39Nu");
        orinalList.add("Ppde");
        orinalList.add("txpb");
        orinalList.add("emWT");
        orinalList.add("Peac");
        orinalList.add("MxMD");
        orinalList.add("wDEE");
        orinalList.add("RkGx");
        orinalList.add("qFVG");
        orinalList.add("7fP5");
        orinalList.add("GDPc");
        orinalList.add("6V6B");
        orinalList.add("sks9");
        orinalList.add("6PdT");
        orinalList.add("84n4");
        orinalList.add("NnQv");
        orinalList.add("YEzP");
        orinalList.add("SpFE");
        orinalList.add("SEN3");
        orinalList.add("i9jt");
        orinalList.add("m6MB");
        orinalList.add("fssp");
        orinalList.add("FYyn");
        orinalList.add("ctAp");
        orinalList.add("eiPP");
        orinalList.add("q4Sz");
        orinalList.add("t6aG");
        orinalList.add("xM4i");
        orinalList.add("PDWP");
        orinalList.add("MBrn");
        orinalList.add("TFEj");
        orinalList.add("QBNA");
        orinalList.add("kijT");
        orinalList.add("gSMS");
        orinalList.add("aDWf");
        orinalList.add("Ti64");
        orinalList.add("mqHc");
        orinalList.add("zngZ");
        orinalList.add("V72T");
        orinalList.add("53nX");
        orinalList.add("atgz");
        orinalList.add("2fy2");
        orinalList.add("HG7m");
        orinalList.add("6eVS");
        orinalList.add("Khcg");
        orinalList.add("n6dG");
        orinalList.add("WyeT");
        orinalList.add("areb");
        orinalList.add("zHhE");
        orinalList.add("ntj7");
        orinalList.add("5c9Z");
        orinalList.add("QEPy");
        orinalList.add("d7Z7");
        orinalList.add("jA6R");
        orinalList.add("Zvb3");
        orinalList.add("Xvec");
        orinalList.add("3NjT");
        orinalList.add("KfuK");
        orinalList.add("bwEx");
        orinalList.add("Xcut");
        orinalList.add("TRBE");
    }

    /**
     * SpecCaptchaTest.out
     * ImageDenoiseTest.cleanImage
     */
    @Test
    public void recognize() throws Exception {
        String tessdataPath = "D:\\opt\\Tess4J\\tessdata";
        String lange = "eng";
//        lange = "chi_sim";
        lange = "num";
        int ok=0;
        for (int i = 0; i < 100; i++) {
            String imgPath = "D:\\ide\\temp\\original\\" + i + ".png";
            imgPath = "D:\\ide\\temp\\denoise\\" + i + ".png";
            String result = Ocr.recognize(tessdataPath, lange, imgPath);
            result=result.replaceAll("\\n", "").replaceAll(" ","");
            log.info(result);
            if(result.equals(orinalList.get(i))){
                ok++;
            }else{
                log.warn("未识别{}",i);
            }

        }
        log.info("{}",ok/100.0);
    }



}