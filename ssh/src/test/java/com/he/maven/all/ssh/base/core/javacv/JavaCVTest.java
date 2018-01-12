package com.he.maven.all.ssh.base.core.javacv;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

/**
 * Created by heyanjing on 2018/1/12 9:58.
 */
public class JavaCVTest {
    public static void randomGrabberFFmpegImage(String filePath, int randomSize)
            throws Exception {
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();

        int ffLength = ff.getLengthInFrames();
        List<Integer> randomGrab = random(ffLength, randomSize);
        int maxRandomGrab = randomGrab.get(randomGrab.size() - 1);
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabImage();
            if (randomGrab.contains(i)) {
                doExecuteFrame(f, i);
            }
            if (i >= maxRandomGrab) {
                break;
            }
            i++;
        }
        ff.stop();
    }

    public static void doExecuteFrame(Frame f, int index) {
        if (null == f || null == f.image) {
            return;
        }
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        opencv_core.Mat mat = converter.convertToMat(f);
        opencv_imgcodecs.imwrite("C:\\temp\\img\\" + index + "sls.png", mat);//存储图像
    }

    public static List<Integer> random(int baseNum, int length) {
        List<Integer> list = new ArrayList<>(length);
        while (list.size() < length) {
            Integer next = (int) (Math.random() * baseNum);
            if (list.contains(next)) {
                continue;
            }
            list.add(next);
        }
        Collections.sort(list);
        return list;
    }
    /**
     * 获取指定视频的帧并保存为图片至指定目录
     * @param videofile  源视频文件路径
     * @param framefile  截取帧的图片存放路径
     * @throws Exception
     */
    public static void fetchFrame(String videofile, String framefile) throws Exception {
        //long start = System.currentTimeMillis();
        //File targetFile = new File(framefile);
        //FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
        //ff.start();
        //int lenght = ff.getLengthInFrames();
        //int i = 0;
        //Frame f = null;
        //while (i < lenght) {
        //    // 过滤前100帧
        //    f = ff.grabFrame();
        //    if ((i > 100) && (f!= null)) {
        //        break;
        //    }
        //    i++;
        //}
        //
        //Buffer[] img = f.image;
        //int owidth = f.imageWidth;
        //int oheight = f.imageHeight;
        //// 对截取的帧进行等比例缩放
        //int width = 270;
        //int height = (int) (((double) width / owidth) * oheight);
        //BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //bi.getGraphics().drawImage(BufferedImage., 0, 0, null);
        //ImageIO.write(bi, "jpg", targetFile);
        ////ff.flush();
        //ff.stop();
        //System.out.println(System.currentTimeMillis() - start);
    }
    @Test
    public void opencvimg() throws Exception {
        randomGrabberFFmpegImage("C:\\temp\\a.mp4", 5);
    }

    @Test
    public void t() throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("C:\\temp\\a.mp4");
        OpenCVFrameConverter.ToIplImage converterToIplImage = new OpenCVFrameConverter.ToIplImage();
        grabber.start();

        grabber.setFrameNumber(1080);//设置帧数
        Frame frame = grabber.grabImage();
        opencv_core.IplImage image = converterToIplImage.convert(frame);
        String img_path = "C:\\temp\\" + "framex-" + String.valueOf(1080) + ".jpg";
        cvSaveImage(img_path, image);

/* 可用
        int frame_count = grabber.getLengthInFrames();
        for (int i = 0; i < frame_count; i += grabber.getFrameRate()) {
            grabber.setFrameNumber(i);
            Frame frame2 = grabber.grabImage();
            if (frame == null) break;
            if (frame.image == null) continue;
            opencv_core.IplImage image = converterToIplImage.convert(frame);
            String img_path2 = "C:\\temp\\img" + "frame-" + String.valueOf(i) + ".jpg";
            cvSaveImage(img_path2, image);
        }*/

        grabber.stop();
    }

    @Test
    public void recordWebcamAndMicrophone() throws Exception {
        JavaCV.recordWebcamAndMicrophone(0, 4, "C:\\temp\\recordWebcamAndMicrophone.mp4", 600, 600, 25);
    }

    @Test
    public void recordPush() throws Exception {
        String inputFile = "C:\\temp\\output.mp4";

        String outputFile = "C:\\temp\\recordPush.mp4";

        JavaCV.recordPush(inputFile, outputFile, 25);
    }

    @Test
    public void frameRecord() throws Exception {
        String inputFile = "C:\\temp\\output.mp4";
        // Decodes-encodes
        String outputFile = "C:\\temp\\record.mp4";

        JavaCV.frameRecord(inputFile, outputFile, 1);
    }

    @Test
    public void recordCamera() throws Exception {
        JavaCV.recordCamera("C:\\temp\\output.mp4", 25);
    }

    @Test
    public void opencv() throws Exception {
        JavaCV.opencv();
    }

}