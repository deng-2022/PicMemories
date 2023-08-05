package com.memory.picmemories.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.memory.picmemories.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lenovo
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2023-03-08 16:07:41
 */
public interface UserService extends IService<User> {
    long userRegister(String username, String password,String phone);

    User userLogin(String username, String password, HttpServletRequest request);

    String getCode(String phoneNumber);

    User codeLogin(String phoneNumber, String code, String rightCode, HttpServletRequest request);

    String userLogout(HttpServletRequest request);

    List<User> userSearch(String username, HttpServletRequest request);

    User getCurrentUser(HttpServletRequest request);

    Page<User> getPage();

    Boolean userDelete(Long id, HttpServletRequest request);

    String userUpdate(User user, User currentUser);

    String userUpdateByAdmin(User user);

    Page<User> selectPage(long currentPage, long pageSize, HttpServletRequest request);
}
