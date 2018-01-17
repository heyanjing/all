package com.he.maven.all.ssh.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by heyanjing on 2018/1/17 11:01.
 */
public class bufferIO {
    private static void read() throws FileNotFoundException, IOException {
        File file = new File("E:\\a.txt");// 指定要读取的文件
        // 获得该文件的缓冲输入流
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = "";// 用来保存每次读取一行的内容
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();// 关闭输入流
    }
    private static void write() throws IOException {
        File file = new File("E:\\a.txt");// 指定要写入的文件
        if (!file.exists()) {// 如果文件不存在则创建
            file.createNewFile();
        }
        // 获取该文件的缓冲输出流
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        // 写入信息
        bufferedWriter.write("你好世界");
        bufferedWriter.newLine();// 表示换行
        bufferedWriter.write("hello world");
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流
    }
    public static void x() throws Exception {
        // 指定要读取文件的缓冲输入字节流
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("F:\\test.jpg"));
        File file = new File("E:\\test.jpg");
        if (file != null) {
            file.createNewFile();
        }
        // 指定要写入文件的缓冲输出字节流
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bb = new byte[1024];// 用来存储每次读取到的字节数组
        int n;// 每次读取到的字节数组的长度
        while ((n = in.read(bb)) != -1) {
            out.write(bb, 0, n);// 写入到输出流
        }
        out.close();// 关闭流
        in.close();
    }
}
