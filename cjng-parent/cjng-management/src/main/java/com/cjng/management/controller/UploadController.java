package com.cjng.management.controller;

import com.cjng.management.pojo.Result;
import com.cjng.management.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    //upload.html本地存储实验
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("username: {}, age: {}, image: {}", username, age, image.getOriginalFilename());
//        String fileName = image.getOriginalFilename();
//        int index = fileName.lastIndexOf(".");
//        String substring = fileName.substring(index);
//        String newFileName = UUID.randomUUID().toString() + substring;
//        log.info("newFileName: {}", newFileName);
//        image.transferTo(new File("E:\\Dark horse\\" + newFileName));
//        return Result.success();
//    }

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("image: {}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("url: {}", url);
        return Result.success(url);
    }
}
