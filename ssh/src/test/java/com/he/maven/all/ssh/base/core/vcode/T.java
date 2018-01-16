package com.he.maven.all.ssh.base.core.vcode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by heyanjing on 2018/1/15 15:16.
 */
public class T {
    private static final Logger log = LoggerFactory.getLogger(T.class);

    @Test
    public void t() throws Exception {
        BufferedImage bi = ImageIO.read(new File("C:\\temp\\rgb\\1.png"));
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minX = bi.getMinX();
        int minY = bi.getMinY();
        log.info("{}", width);
        log.info("{}", height);
        log.info("{}", minX);
        log.info("{}", minY);

        //int argb = bi.getRGB(5, 5);
        //log.info("{}", Integer.toHexString(argb & 0xFFFFFF));// b0d7cc
        //int r = (argb & 0xff0000) >> 16;
        //int g = (argb & 0xff00) >> 8;
        //int b = (argb & 0xff);
        //log.info("{}", r);
        //log.info("{}", g);
        //log.info("{}", b);


        //double greyAve = 0.0;
        int sum=0;
        int[][] rgbArr = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int argb = bi.getRGB(w, h);
                log.info("argb={},h={},w={}", argb, h, w);
                sum+=getImageRgb(argb & 0xFFFFFF);
                rgbArr[h][w]=getImageRgb(argb & 0xFFFFFF);
                //备注：应为使用getRGB(h,w)获取的该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(h, w) & 0xFFFFFF。
                //log.info("{}", Integer.toHexString(argb));//ffb0d7cc
                //log.info("{}", Integer.toHexString(argb & 0xFFFFFF));// b0d7cc
                //int r = (argb & 0xff0000) >> 16;
                //int g = (argb & 0xff00) >> 8;
                //int b = (argb & 0xff);
                //greyAve += r * 0.3 + g * 0.59 + b * 0.11;
            }
        }
        log.info("{}",sum/(height*width));
        //greyAve /= width * height;
        //log.info("{}",greyAve);
        //int[][] rgbArr = new int[height][width];
        //for (int h = 0; h < height; h++) {
        //    for (int w = 0; w < width; w++) {
        //        int argb = bi.getRGB(h, w);
        //        int r = (argb & 0xff0000) >> 16;
        //        int g = (argb & 0xff00) >> 8;
        //        int b = (argb & 0xff);
        //        double grey = r * 0.333 + g * 0.333 + b * 0.333;
        //        int rgb = grey > greyAve ? 255 : 0;
        //        rgbArr[h][w] = rgb;
        //    }
        //}
    }
    private static int getImageRgb(int i) {
        String argb = Integer.toHexString(i);// 将十进制的颜色值转为十六进制
        // argb分别代表透明,红,绿,蓝 分别占16进制2位
        int r = Integer.parseInt(argb.substring(0, 2),16);//后面参数为使用进制
        int g = Integer.parseInt(argb.substring(2, 4),16);
        int b = Integer.parseInt(argb.substring(4, 6),16);
        int result=(int)((r+g+b)/3);
        return result;
    }
}
