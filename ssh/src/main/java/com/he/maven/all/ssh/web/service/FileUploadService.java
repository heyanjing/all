package com.he.maven.all.ssh.web.service;

import com.he.maven.all.ssh.base.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by heyanjing on 2017/12/19 10:37.
 */
@Service
public class FileUploadService {
    private static final Logger log = LoggerFactory.getLogger(FileUploadService.class);


    public Result upload(MultipartFile file) {
        Result result;
        try {
            //保存文件
            file.transferTo(new File("D:/Temp" + File.separator + file.getOriginalFilename()));
            result = Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            result = Result.failure();
        }
        return result;
    }

}
