package com.he.maven.all.ssh.test.ffmpeg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by heyanjing on 2018/1/11 9:19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoInfo {
    private static final Logger log = LoggerFactory.getLogger(VideoInfo.class);
    // 视频路径
    private String ffmpegApp;
    // 视频时
    private int hours;
    // 视频分
    private int minutes;
    // 视频秒
    private float seconds;
    // 视频width
    private int width;
    // 视频height
    private int heigt;


    public VideoInfo(String ffmpegApp) {
        this.ffmpegApp = ffmpegApp;
    }

    public String toString() {
        return "time: " + hours + ":" + minutes + ":" + seconds + ", width = " + width + ", height= " + heigt;
    }

    public void getInfo(String videoFilename) throws IOException, InterruptedException {
        String tmpFile = videoFilename + ".tmp.png";
        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y", "-i", videoFilename, "-vframes", "1", "-ss", "0:0:0", "-an", "-vcodec", "png", "-f", "rawvideo", "-s", "100*100", tmpFile);

        Process process = processBuilder.start();

        InputStream stderr = process.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line;
        // 打印 sb，获取更多信息。 如 bitrate、width、heigt
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        new File(tmpFile).delete();

        log.info("video info:" + sb);
        Pattern pattern = Pattern.compile("Duration: (.*?),");
        Matcher matcher = pattern.matcher(sb);

        if (matcher.find()) {
            String time = matcher.group(1);
            log.info(time);
            String[] parts = time.split(":");
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
            seconds = Float.parseFloat(parts[2]);
        }

        pattern = Pattern.compile("w:\\d+ h:\\d+");
        matcher = pattern.matcher(sb);

        if (matcher.find()) {
            String wh = matcher.group();
            log.info(wh);
            // w:100 h:100
            String[] strs = wh.split("\\s+");
            if (strs != null && strs.length == 2) {
                width = Integer.parseInt(strs[0].split(":")[1]);
                heigt = Integer.parseInt(strs[1].split(":")[1]);
            }
        }

        process.waitFor();
        if (br != null)
            br.close();
        if (isr != null)
            isr.close();
        if (stderr != null)
            stderr.close();
    }


    public static void main(String[] args) {
        VideoInfo videoInfo = new VideoInfo("C:\\ffmpeg-20180102-57d0c24-win64-static\\bin\\ffmpeg.exe");
        try {
            videoInfo.getInfo("C:\\temp\\a.mp4");
            log.warn("{}", videoInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
