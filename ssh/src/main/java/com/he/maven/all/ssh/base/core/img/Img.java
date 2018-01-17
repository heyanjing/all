package com.he.maven.all.ssh.base.core.img;

import com.he.maven.all.ssh.base.core.Guava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by heyanjing on 2018/1/12 10:50.
 */
public class Img {
    private static final Logger log = LoggerFactory.getLogger(Img.class);
    private static final Pattern pattern = Pattern.compile("data:((\\w+)/(\\w+));base64,(.+)");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static List<String> base64ToImg(String imgBase64, String fileDir) throws IOException {
        List<String> result = Guava.newArrayList();
        Matcher matcher = pattern.matcher(imgBase64);
        while (matcher.find()) {
            log.info(matcher.group());
            String suffix = matcher.group(3);
            String base64 = matcher.group(4);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; i++) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            String img = DATE_TIME_FORMATTER.format(LocalDateTime.now()) + "." + suffix;
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(fileDir, img)));
            byte[] bb = new byte[1024];
            int len;
            while ((len = in.read(bb)) != -1) {
                out.write(bb, 0, len);
            }
            out.close();
            in.close();
            result.add(img);
        }
        return result;
    }


    /**
     * 给图片增加文字水印
     *
     * @param imgPath    -要添加水印的图片路径
     * @param outImgPath -输出路径
     * @param text-文字
     * @param font       -字体
     * @param color      -颜色
     * @param x          -文字位于当前图片的横坐标
     * @param y          -文字位于当前图片的竖坐标
     */
    public void mark(String imgPath, String outImgPath, String text, Font font, Color color, int x, int y) {
        try {
            // 读取原图片信息
            File imgFile = null;
            Image img = null;
            if (imgPath != null) {
                imgFile = new File(imgPath);
            }
            if (imgFile != null && imgFile.exists() && imgFile.isFile() && imgFile.canRead()) {
                img = ImageIO.read(imgFile);
            }
            int imgWidth = img.getWidth(null);
            int imgHeight = img.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            mark(bufImg, img, text, font, color, x, y);
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给图片增加图片水印
     *
     * @param inputImg  -源图片，要添加水印的图片
     * @param markImg   - 水印图片
     * @param outputImg -输出图片(可以是源图片)
     * @param width     - 水印图片宽度
     * @param height    -水印图片高度
     * @param x         -横坐标，相对于源图片
     * @param y         -纵坐标，同上
     */
    public void mark(String inputImg, String markImg, String outputImg, int width, int height, int x, int y) {
        // 读取原图片信息
        File inputImgFile = null;
        File markImgFile = null;
        Image img = null;
        Image mark = null;
        try {
            if (inputImg != null && markImg != null) {
                inputImgFile = new File(inputImg);
                markImgFile = new File(markImg);
            }
            if (inputImgFile != null && inputImgFile.exists() && inputImgFile.isFile() && inputImgFile.canRead()) {

                img = ImageIO.read(inputImgFile);

            }
            if (markImgFile != null && markImgFile.exists() && markImgFile.isFile() && markImgFile.canRead()) {

                mark = ImageIO.read(markImgFile);

            }
            int imgWidth = img.getWidth(null);
            int imgHeight = img.getHeight(null);
            BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            mark(bufImg, img, mark, width, height, x, y);
            FileOutputStream outImgStream = new FileOutputStream(outputImg);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 加文字水印
    public void mark(BufferedImage bufImg, Image img, String text, Font font, Color color, int x, int y) {
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
        g.dispose();
    }

    // 加图片水印
    public void mark(BufferedImage bufImg, Image img, Image markImg, int width, int height, int x, int y) {
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
        g.drawImage(markImg, x, y, width, height, null);
        g.dispose();
    }
}
