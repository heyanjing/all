package com.he.maven.all.ssh.base.core.ffmpeg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by heyanjing on 2018/1/11 13:42.
 * http://ffmpeg.org/ 官网地址
 */
public class Ffmpeg {
    private static final Logger log = LoggerFactory.getLogger(Ffmpeg.class);
    private static Pattern pattern = Pattern.compile("Duration: (.*?),");

    /**
     * 从视频文件中截取一张图片
     *
     * @param ffmpegPath     ffmpeg的路径
     * @param sourceFilePath 源文件路径
     * @param second         截取第几秒的图
     * @param outFilePath    输出图片位置
     * @return 返回文件信息
     * @throws IOException IOException
     */
    public static String video2Img(String ffmpegPath, String sourceFilePath, String second, String outFilePath) throws IOException, InterruptedException {
        if (outFilePath == null) {
            outFilePath = sourceFilePath + ".jpg";
        }
        StringBuilder sb = new StringBuilder();
        List<String> cmd = new ArrayList();
        cmd.add(ffmpegPath);
        //覆盖输出文件而不需询问
        cmd.add("-y");
        //当用作输入选项（在-i之前）时，将在此输入文件中寻找指定的时间位置。
        cmd.add("-ss");
        cmd.add(second);
        //输入文件的路径
        cmd.add("-i");
        cmd.add(sourceFilePath);
        //设置要输出的视频帧的数量
        cmd.add("-vframes");
        cmd.add("1");
        //禁用录音
        cmd.add("-an");
        //强制输入或输出文件格式。格式通常是自动检测输入文件，并从输出文件的文件扩展名中猜测出来的，所以在大多数情况下这个选项是不需要的。
        cmd.add("-f");
        cmd.add("mjpeg");
        //设置输出大小，格式是'宽x高'（默认 - 与源相同）。
        //cmd.add("-s");
        //cmd.add("100*100");
        cmd.add(outFilePath);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(cmd);
        builder.redirectErrorStream(true);
        BufferedReader buf = null;
        String line;
        try {
            Process p = builder.start();
            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = buf.readLine()) != null) {
                log.debug(line);
                sb.append(line);
            }
            //if (outFilePath == null) {
            //    new File(sourceFilePath + ".jpg").delete();
            //}
            String str = sb.toString();
            p.waitFor();//直到上面的命令执行完，才向下执行

            Matcher matcher = pattern.matcher(sb);
            if (matcher.find()) {
                String time = matcher.group(1);
                log.info(time);
                String[] parts = time.split(":");
                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);
                float seconds = Float.parseFloat(parts[2]);
                log.info("{}:{}:{}", hours, minutes, seconds);
            }
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buf != null) {
                buf.close();
            }
        }
        return sb.toString();
    }

    /**
     * 视频格式转换为另一种格式
     *
     * @param ffmpegPath     ffmpeg的路径
     * @param sourceFilePath 源文件路径
     * @param outFilePath    输出图片位置
     * @return 返回文件信息
     * @throws IOException IOException
     */
    public static String video2Video(String ffmpegPath, String sourceFilePath, String outFilePath) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        List<String> convert = new ArrayList<String>();
        // 添加转换工具路径
        convert.add(ffmpegPath);
        // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add("-i");
        // 添加要转换格式的视频文件的路径
        convert.add(sourceFilePath);
        //指定转换的质量
        convert.add("-qscale");
        convert.add("6");
        //设置音频码率
        convert.add("-ab");
        convert.add("64");
        //设置声道数
        convert.add("-ac");
        convert.add("2");
        //设置声音的采样频率
        convert.add("-ar");
        convert.add("22050");
        //设置帧频
        convert.add("-r");
        convert.add("24");
        // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add("-y");
        convert.add(outFilePath);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(convert);
        builder.redirectErrorStream(true);
        Process process1 = builder.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process1.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            log.debug(line);
            sb.append(line);
        }
        process1.waitFor();//直到上面的命令执行完，才向下执行
        return sb.toString();
    }


}
