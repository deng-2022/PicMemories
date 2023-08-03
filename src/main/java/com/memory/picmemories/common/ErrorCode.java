package com.memory.picmemories.common;

/**
 * @author 邓哈哈
 * 2023/3/19 22:10
 * Function: 自定义异常态
 * Version 1.0
 */

public enum ErrorCode {
    PARMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    UPDATE_ERROR(40002, "操作数据库失败", ""),
    NOT_LOGIN(40003, "未登录", ""),
    NOT_REGISTER(40004, "未注册", ""),
    NO_AUTH(40005, "无权限", ""),
    CODE_ERROR(40006, "验证码错误", ""),
    SYSTEM_ERROR(10000, "系统内部异常", "");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码描述
     */
    private final String description;

    /**
     * @param code        状态码
     * @param message     信息
     * @param description 描述
     */
    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
