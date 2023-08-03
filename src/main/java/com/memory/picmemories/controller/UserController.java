package com.memory.picmemories.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.common.BaseResponse;
import com.memory.picmemories.common.ErrorCode;
import com.memory.picmemories.common.ResultUtils;
import com.memory.picmemories.exception.BusinessException;
import com.memory.picmemories.model.User;
import com.memory.picmemories.model.request.user.UserLoginRequest;
import com.memory.picmemories.model.request.user.UserRegisterRequest;
import com.memory.picmemories.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.memory.picmemories.common.ErrorCode.PARMS_ERROR;
import static com.memory.picmemories.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 邓哈哈
 * 2023/8/2 17:40
 * Function:
 * Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isAnyBlank(username, password)) {

            throw new BusinessException(PARMS_ERROR);
        }

        long id = userService.userRegister(username, password);
        return ResultUtils.success(id);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(PARMS_ERROR);
        }

        User userLogin = userService.userLogin(username, password, request);
        return ResultUtils.success(userLogin);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> userDelete(Long id, HttpServletRequest request) {
        //controller对参数的校验
        if (id == null)
            throw new BusinessException(PARMS_ERROR);

        Boolean userDelete = userService.userDelete(id, request);
        return ResultUtils.success(userDelete);

    }

    @PostMapping("/update")
    public BaseResponse<String> userUpdate(@RequestBody User user, HttpServletRequest request) {
        // controller对参数的校验
        if (user == null)
            throw new BusinessException(PARMS_ERROR);

        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (currentUser == null)
            throw new BusinessException(PARMS_ERROR);

        String userUpdate = userService.userUpdate(user, currentUser);
        return ResultUtils.success(userUpdate);
    }

    @PostMapping("/logout")
    public BaseResponse<String> userLogout(HttpServletRequest request) {
        //controller对参数的校验
        if (request == null)
            throw new BusinessException(PARMS_ERROR);

        String userLogout = userService.userLogout(request);
        return ResultUtils.success(userLogout);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        //controller对参数的校验

        User currentUser = userService.getCurrentUser(request);
        return ResultUtils.success(currentUser);
    }

    @GetMapping("/listPage")
    public BaseResponse<Page<User>> getPage() {
        Page<User> result = userService.getPage();
        return ResultUtils.success(result);
    }

}
