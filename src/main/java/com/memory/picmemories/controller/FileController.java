package com.memory.picmemories.controller;

import com.memory.picmemories.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 邓哈哈
 * 2023/8/8 21:00
 * Function:
 * Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传接口
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestPart("file") MultipartFile file){
        String imgFileStr = fileService.upload(file);
        return buildResult(imgFileStr);
    }

    /**
     * 测试返回拼装，根据公司自己封装的统一返回去写
     * @param str
     * @return
     */
    private Map<String,Object> buildResult(String str){
        Map<String, Object> result = new HashMap<>();
        //判断字符串用lang3下的StringUtils去判断，这块我就不引入新的依赖了
        if(str== null || "".equals(str)){
            result.put("code",10000);
            result.put("msg","图片上传失败");
            result.put("data",null);
        }else{
            result.put("code",200);
            result.put("msg","图片上传成功");
            result.put("data",str);
        }
        return result;
    }

}