package com.he.maven.all.ssh.base.core.javacv;

import org.bytedeco.javacpp.avcodec;
import org.junit.Test;

/**
 * Created by heyanjing on 2018/1/12 10:44.
 */
public class AudioConvertTest {
    @Test
    public void convert() throws Exception {
        AudioConvert.convert("C:\\temp\\a.mp3", "C:\\temp\\eguid.mp3", avcodec.AV_CODEC_ID_MP3, 8000, 16,1);
    }

}