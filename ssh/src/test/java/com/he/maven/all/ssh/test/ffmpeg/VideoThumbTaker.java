package com.he.maven.all.ssh.test.ffmpeg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyanjing on 2018/1/11 9:25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoThumbTaker {
    private static final Logger log = LoggerFactory.getLogger(VideoThumbTaker.class);
    protected String ffmpegApp;


    @SuppressWarnings("unused")
    /****
     * 获取指定时间内的图片
     * @param videoFilename:视频路径
     * @param thumbFilename:图片保存路径
     * @param width:图片长
     * @param height:图片宽
     * @param hour:指定时
     * @param min:指定分
     * @param sec:指定秒
     * @throws IOException
     * @throws InterruptedException
     */
    public void getThumb(String videoFilename, String thumbFilename, int width, int height, int hour, int min, float sec) throws IOException, InterruptedException {
        ProcessBuilder processBuilderx = new ProcessBuilder(ffmpegApp, "-y", "-i", videoFilename, "-vframes", "1", "-ss", "0:0:0", "-an", "-vcodec", "png", "-f", "rawvideo", "-s", "100*100", "C:\\temp\\a.png");
        processBuilderx.start();

        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y", "-i", videoFilename, "-vframes", "1", "-ss", hour + ":" + min + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height, "-an", thumbFilename);

        Process process = processBuilder.start();
        InputStream stderr = process.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) ;
        process.waitFor();

        if (br != null)
            br.close();
        if (isr != null)
            isr.close();
        if (stderr != null)
            stderr.close();
    }


    /**
     * Java使用FFmpeg获取视频的缩略图
     *
     * @param ffmpegPath   为FFmpeg.exe所在路径
     * @param upFilePath   为视频的所在路径（本地路径）
     * @param mediaPicPath 为缩略图的存储路径（D:/test.jpg）
     */
    public static void handler(String ffmpegPath, String upFilePath, String mediaPicPath) {
        //new ProcessBuilder(ffmpegApp, "-y", "-i", videoFilename, "-vframes", "1", "-ss", hour + ":" + min + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height, "-an", thumbFilename);
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("5"); // 添加起始时间为第5秒
        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("0.001"); // 添加持续时间为1毫秒
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("500*400"); // 添加截取的图片大小为350*240
        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

        ProcessBuilder builder = new ProcessBuilder();
        try {

            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            // 因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            builder.start();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * 视频转码
     *
     * @param ffmpegPath   转码工具的存放路径
     * @param upFilePath   用于指定要转换格式的文件,要截图的视频源文件
     * @param codcFilePath 格式转换后的的文件保存路径
     * @param mediaPicPath 截图保存路径
     * @return
     * @throws Exception
     */
    public static boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,
                                        String mediaPicPath) throws Exception {
        // 创建一个List集合来保存转换视频文件为flv格式的命令
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); // 添加转换工具路径
        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add(upFilePath); // 添加要转换格式的视频文件的路径
        convert.add("-qscale");     //指定转换的质量
        convert.add("6");
        convert.add("-ab");        //设置音频码率
        convert.add("64");
        convert.add("-ac");        //设置声道数
        convert.add("2");
        convert.add("-ar");        //设置声音的采样频率
        convert.add("22050");
        convert.add("-r");        //设置帧频
        convert.add("24");
        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add(codcFilePath);

        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("5"); // 添加起始时间为第17秒
        //cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        //cutpic.add("0.001"); // 添加持续时间为1毫秒
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("800*280"); // 添加截取的图片大小为350*240
        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            Process process1 = builder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process1.getInputStream()));
            String line = null;
            log.info("{}", process1.isAlive());
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            int i = process1.waitFor();
            log.error("{}", i);
            log.info("{}", process1.isAlive());
            log.error("over");


            //builder.command(cutpic);
            //builder.redirectErrorStream(true);
            //// 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            ////因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            //Process process2 = builder.start();
            //log.info("{}", process2.isAlive());
            //int i = process2.waitFor();
            //log.error("{}", i);
            //log.info("{}", process2.isAlive());


        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }

    public static void main(String[] args) {
        VideoThumbTaker videoThumbTaker = new VideoThumbTaker("C:\\ffmpeg-20180102-57d0c24-win64-static\\bin\\ffmpeg.exe");
        try {
            //videoThumbTaker.getThumb("C:\\temp\\a.mp4", "C:\\temp\\少女時代.png", 800, 600, 0, 0, 5);
            //handler("C:\\ffmpeg-20180102-57d0c24-win64-static\\bin\\ffmpeg.exe","C:\\temp\\a.mp4","C:\\temp\\少女時代2.png");
            executeCodecs("C:\\ffmpeg-20180102-57d0c24-win64-static\\bin\\ffmpeg.exe", "C:\\temp\\a.mp4", "C:\\temp\\a-copy.flv", "C:\\temp\\少女時代3.png");
            log.warn("finish");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
