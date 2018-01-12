package com.he.maven.all.ssh.base.core.img;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Font;

/**
 * Created by heyanjing on 2018/1/12 10:55.
 */
public class ImgTest {
    Img img;
    @Before
    public void setUp() throws Exception {
        img=new Img();
    }

    @Test
    public void mark() throws Exception {
        img.mark("C:\\temp\\a.jpg","C:\\temp\\mark.jpg","你好",new Font("宋体", Font.PLAIN, 30), Color.yellow,1150,460);
    }

    @Test
    public void mark1() throws Exception {
        img.mark("C:\\temp\\a.jpg","C:\\temp\\x.png","C:\\temp\\mark1.jpg",100,100,100,100);
    }

}