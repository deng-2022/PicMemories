package com.memory.picmemories.model.DTO;

import com.memory.picmemories.model.entity.User;
import lombok.Data;

/**
 * @author 邓哈哈
 * 2023/8/6 12:04
 * Function:
 * Version 1.0
 */

@Data
public class UserDTO extends User {
    private String session_key;
    private String openid;
}
