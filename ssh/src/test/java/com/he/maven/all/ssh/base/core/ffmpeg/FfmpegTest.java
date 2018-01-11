package com.he.maven.all.ssh.base.core.ffmpeg;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by heyanjing on 2018/1/11 14:19.
 */
public class FfmpegTest {
    private static final Logger log = LoggerFactory.getLogger(FfmpegTest.class);
    @Test
    public void getInfo() throws Exception {
        log.info(Ffmpeg.video2Img("C:\\ffmpeg-20180102-57d0c24-win64-static\\bin\\ffmpeg.exe","C:\\temp\\a.mp4","141","C:\\temp\\a.jpg"));
        log.info(Ffmpeg.video2Video("C:\\ffmpeg-20180102-57d0c24-win64-static\\bin\\ffmpeg.exe","C:\\temp\\a.mp4","C:\\temp\\a.avi"));
    }

}