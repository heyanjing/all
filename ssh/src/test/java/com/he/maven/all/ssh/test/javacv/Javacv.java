package com.he.maven.all.ssh.test.javacv;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.junit.Test;

import javax.swing.JFrame;

/**
 * Created by heyanjing on 2018/1/11 15:47.
 */
public class Javacv {
    public static void main(String[] args) throws Exception {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();   //开始获取摄像头数据
        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);

        while (true) {
            if (!canvas.isDisplayable()) {//窗口是否关闭
                grabber.stop();//停止抓取
                System.exit(2);//退出
            }
            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像

            Thread.sleep(50);//50毫秒刷新一次图像
        }
    }

    @Test
    public void test() throws FrameGrabber.Exception {
        FFmpegFrameGrabber fg = new FFmpegFrameGrabber("C:\\temp\\a.mp4");
        fg.start();
        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);

        while (true) {
            if (!canvas.isDisplayable()) {//窗口是否关闭
                fg.stop();//停止抓取
                System.exit(2);//退出
            }
            canvas.showImage(fg.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像

        }
    }
}
