package com.he.maven.all.ssh.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by heyanjing on 2018/1/16 11:50.
 */
public class T {
    private static final Logger log = LoggerFactory.getLogger(T.class);

    @Test
    public void t() {
        String base = "data:image/png;base64,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";
        //base = "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";
        Pattern pattern = Pattern.compile("data:((\\w+)/(\\w+));base64,(.+)");
        Matcher matcher = pattern.matcher(base);
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
            GenerateImage(matcher.group(4));
        }
    }

    public static boolean GenerateImage(String imgStr) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "C:\\temp\\img\\b.png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
