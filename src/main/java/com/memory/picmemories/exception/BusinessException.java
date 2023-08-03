package com.memory.picmemories.exception;


import com.memory.picmemories.common.ErrorCode;

/**
 * @author 邓哈哈
 * 2023/3/20 20:07
 * Function: 自定义异常对象
 * Version 1.0
 */

public class BusinessException extends RuntimeException {
    /**
     * 状态码
     */
    private final int code;

    /**
     * 异常描述
     */
    private final String description;

    /**
     * 封装局部自定义异常
     *
     * @param message     异常信息
     * @param code        异常状态码
     * @param description 异常描述
     */
    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    /**
     * 封装全局自定义异常(无描述)
     *
     * @param errorCode 自定义异常态
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    /**
     * 封装全局自定义异常(有描述)
     *
     * @param errorCode   自定义异常态
     * @param description 异常描述
     */
    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
