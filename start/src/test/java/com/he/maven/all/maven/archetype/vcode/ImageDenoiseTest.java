package com.he.maven.all.maven.archetype.vcode;

import org.junit.Test;

import java.io.File;

/**
 * Created by heyanjing on 2018/1/13 22:49.
 */
public class ImageDenoiseTest {
    @Test
    public void cleanImage() throws Exception {
        for (int i = 0; i <100; i++) {
            ImageDenoise.cleanImage(new File("D:\\ide\\temp\\original", i + ".png"), "D:\\ide\\temp\\denoise");
        }
    }


}