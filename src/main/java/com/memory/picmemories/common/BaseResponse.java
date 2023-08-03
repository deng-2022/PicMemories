package com.memory.picmemories.common;

/**
 * @author 邓哈哈
 * 2023/3/19 21:44
 * Function: 封装通用返回对象
 * Version 1.0
 */

public class BaseResponse<T> {
    /**
     * 状态码
     */
    private int code;

    /**
     * 数据
     */
    private T data;

    /**
     * 信息
     */
    private String message;

    /**
     * 描述
     */
    private String description;

    /**
     * 封装执行成功的结果
     *
     * @param code        状态码
     * @param data        数据
     * @param message     信息
     * @param description 描述
     */
    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    /**
     * 封装全局自定义异常
     *
     * @param code        状态码
     * @param message     信息
     * @param description 描述
     */
    public BaseResponse(int code, String message, String description) {
        this.code = code;
        this.data = null;
        this.message = message;
        this.description = description;
    }

    /**
     * 封装局部自定义异常
     *
     * @param errorCode 自定义异常态
     */
    public BaseResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = errorCode.getMessage();
        this.description = errorCode.getDescription();
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
