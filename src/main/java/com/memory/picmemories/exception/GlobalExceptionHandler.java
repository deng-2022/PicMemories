package com.memory.picmemories.exception;

import com.memory.picmemories.common.BaseResponse;
import com.memory.picmemories.common.ErrorCode;
import com.memory.picmemories.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 邓哈哈
 * 2023/3/20 20:59
 * Function: 全局异常处理器
 * Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获自定义异常对象
     *
     * @param e 自定义异常
     * @return 封装返回执行失败异常信息
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse locallyCustomException(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        log.info("" + e.getCode());
        log.info(e.getMessage());
        log.info(e.getDescription());
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    /**
     * 捕获其他异常
     *
     * @param e 其他异常
     * @return 封装返回请求失败异常信息
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse globalCustomException(Exception e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }


}
