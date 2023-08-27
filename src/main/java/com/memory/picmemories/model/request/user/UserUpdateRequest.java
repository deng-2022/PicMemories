package com.memory.picmemories.model.request.user;

import com.memory.picmemories.model.Code2Session.Code2Session;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 邓哈哈
 * 2023/8/11 23:21
 * Function:
 * Version 1.0
 */
@Data
public class UserUpdateRequest {
    private String session_key;
    private String username;
    private String password;
    private String phone;
}
