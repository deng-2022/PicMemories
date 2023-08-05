package com.memory.picmemories.model.request.user;

import lombok.Data;

/**
 * @author 邓哈哈
 * 2023/3/10 13:26
 * Function: 用户注册参数
 * Version 1.0
 */

@Data
public class UserRegisterRequest {
    private String username;
    private String password;
    private String phone;
}
