package com.memory.picmemories.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.memory.picmemories.model.Code2Session.Code2Session;
import com.memory.picmemories.model.entity.User;
import com.memory.picmemories.model.request.user.UserUpdateRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lenovo
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2023-03-08 16:07:41
 */
public interface UserService extends IService<User> {
    long userRegister(String username, String password, String phone);

    User userLogin(String code, String username, String password, HttpServletRequest request);

    String userUpdate(UserUpdateRequest user);

    String userLogout(String session_key);

    User getCurrentUser(String session_key);

    User adminLogin(String username, String password, HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);

    Page<User> getPage();

}
