package com.memory.picmemories.controller;

import com.memory.picmemories.common.BaseResponse;
import com.memory.picmemories.common.ResultUtils;
import com.memory.picmemories.exception.BusinessException;
import com.memory.picmemories.model.entity.User;
import com.memory.picmemories.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.memory.picmemories.common.ErrorCode.PARMS_ERROR;

/**
 * @author 邓哈哈
 * 2023/8/10 20:41
 * Function:
 * Version 1.0
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Resource
    private UploadService uploadService;

    @GetMapping("/user")
    public BaseResponse<User> getUserByUrl(String url) {
        //controller对参数的校验
        if (StringUtils.isAnyBlank(url)) {
            throw new BusinessException(PARMS_ERROR, "输入内容不能为空");
        }
        return ResultUtils.success(new User());
    }
}
