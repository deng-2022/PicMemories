package com.memory.picmemories.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.common.BaseResponse;
import com.memory.picmemories.common.ResultUtils;
import com.memory.picmemories.exception.BusinessException;
import com.memory.picmemories.model.Code2Session.Code2Session;
import com.memory.picmemories.model.entity.User;
import com.memory.picmemories.model.request.user.UserLoginRequest;
import com.memory.picmemories.model.request.user.UserRegisterRequest;
import com.memory.picmemories.model.request.user.UserUpdateRequest;
import com.memory.picmemories.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest user) {
        //controller对参数的校验
        String username = user.getUsername();
        String password = user.getPassword();
        String phone = user.getPhone();
        if (StringUtils.isAnyBlank(username, password, phone)) {

            throw new BusinessException(PARMS_ERROR, "输入内容不能为空");
        }

        long id = userService.userRegister(username, password, phone);
        return ResultUtils.success(id, "注册成功");
    }

    /**
     * 用户登录
     *
     * @param user    用户信息
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest user, HttpServletRequest request) {
        //controller对参数的校验
        String code = user.getCode();
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isAnyBlank(code, username, password)) {
            throw new BusinessException(PARMS_ERROR, "输入内容不能为空");
        }

        User userLogin = userService.userLogin(code, username, password, request);
        return ResultUtils.success(userLogin, "登录成功");
    }

    /**
     * 用户修改信息
     *
     * @param user         修改信息
     * @param
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<String> userUpdate(@RequestBody UserUpdateRequest user) {
        // controller对参数的校验
        if (user == null) {
            throw new BusinessException(PARMS_ERROR, "请求参数错误");
        }

        String userUpdate = userService.userUpdate(user);
        return ResultUtils.success(userUpdate);
    }

    /**
     * 用户注销
     *
     * @param code2Session code2Session
     * @return 是否注销
     */
    @PostMapping("/logout")
    public BaseResponse<String> userLogout(@RequestBody Code2Session code2Session) {
        //controller对参数的校验
        String session_key = code2Session.getSession_key();
        if (StringUtils.isAnyBlank(session_key)) {
            throw new BusinessException(PARMS_ERROR, "请求参数错误");
        }

        String userLogout = userService.userLogout(session_key);
        return ResultUtils.success(userLogout);
    }

    /**
     * 获取当前用户登录态(用户前台)
     *
     * @param code2Session code2Session
     * @return 用户信息
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(Code2Session code2Session) {
        //controller对参数的校验
        String session_key = code2Session.getSession_key();
        if (StringUtils.isAnyBlank(session_key)) {
            throw new BusinessException(PARMS_ERROR, "无权限");
        }

        User currentUser = userService.getCurrentUser(session_key);
        return ResultUtils.success(currentUser);
    }

    /**
     * 获取当前用户登录态(管理后台)
     *
     * @param request request
     * @return 管理员信息
     */
    @GetMapping("/loginUser")
    public BaseResponse<User> getLoginUser(HttpServletRequest request) {
        // controller对参数的校验
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (currentUser == null)
            throw new BusinessException(PARMS_ERROR);

        User user = userService.getLoginUser(request);
        return ResultUtils.success(user);
    }

    /**
     * 在线用户列表(管理员后台)
     *
     * @return
     */
    @GetMapping("/listPage")
    public BaseResponse<Page<User>> getPage() {
        Page<User> result = userService.getPage();
        return ResultUtils.success(result);
    }

    /**
     * 管理员登录
     *
     * @param user    管理员信息
     * @param request
     * @return
     */
    @PostMapping("/login/admin")
    public BaseResponse<User> adminLogin(@RequestBody UserLoginRequest user, HttpServletRequest request) {
        //controller对参数的校验
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(PARMS_ERROR, "输入内容不能为空");
        }

        User userLogin = userService.adminLogin(username, password, request);
        return ResultUtils.success(userLogin, "登录成功");
    }

}
