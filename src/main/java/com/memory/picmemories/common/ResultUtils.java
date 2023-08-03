package com.memory.picmemories.common;

/**
 * @author 邓哈哈
 * 2023/3/19 21:47
 * Function: 封装调用通用返回对象
 * Version 1.0
 */

public class ResultUtils {
    /**
     * 封装调用执行成功
     *
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 执行成功信息
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok", "");
    }

    /**
     * 封装调用执行失败
     *
     * @param code        异常状态码
     * @param message     异常信息
     * @param description 异常描述
     * @return 执行异常信息
     */
    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse<>(code, message, description);
    }

    /**
     * @param errorCode 全局自定义异常
     * @return 执行异常信息
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }
}
