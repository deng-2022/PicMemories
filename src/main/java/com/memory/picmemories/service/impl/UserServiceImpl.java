package com.memory.picmemories.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.picmemories.exception.BusinessException;
import com.memory.picmemories.mapper.UserMapper;
import com.memory.picmemories.model.DTO.UserDTO;
import com.memory.picmemories.model.entity.User;
import com.memory.picmemories.model.Code2Session.Code2Session;
import com.memory.picmemories.model.request.user.UserUpdateRequest;
import com.memory.picmemories.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.memory.picmemories.common.ErrorCode.*;
import static com.memory.picmemories.constant.UserConstant.SALT;
import static com.memory.picmemories.constant.UserConstant.USER_LOGIN_STATE;


/**
 * @author 邓哈哈
 * 针对表【user(用户)】的数据库操作Service实现
 * 2023-03-08 16:07:41
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();



//    属性	类型	必填	说明
//    appid	string	是	小程序 appId
//    secret	string	是	小程序 appSecret
//    js_code	string	是	登录时获取的 code，可通过wx.login获取
//    grant_type	string	是	授权类型，此处只需填写 authorization_code
//    返回参数

    /**
     * 用户注册
     *
     * @param username 昵称
     * @param password 密码
     * @param phone    手机号
     * @return 用户id
     */
    @Override
    public long userRegister(String username, String password, String phone) {
        // 1.校验
        // 1.1.昵称, 密码, 手机号不能为空
        if (StringUtils.isAnyBlank(username, password, phone)) throw new BusinessException(PARMS_ERROR);

        // 1.2.昵称不小于2位, 不大于10位
        if (username.length() < 2 || username.length() > 10) throw new BusinessException("昵称不符合要求", 50000, "昵称长度不符合要求");

        // 1.3.昵称不包含特殊字符
        String pattern = "^[\\u4e00-\\u9fa5A-Za-z\\d_]+$";
        if (!Pattern.matches(pattern, username)) throw new BusinessException("昵称不符合要求", 50001, "昵称包含特殊字符");

        // 1.4.密码不小于6位, 不大于10位
        if (password.length() < 6 || password.length() > 10) throw new BusinessException("密码不符合要求", 60000, "密码长度不符合要求");

        // 1.5.手机号只能为大陆手机号，符合要求
        pattern = "^(13\\d|14[5|7]|15[0|123456789]|18[0|12356789])\\d{8}$";
        if (!Pattern.matches(pattern, phone)) throw new BusinessException("手机号不符合要求", 50001, "手机号格式有误");

        // 1.6.昵称不能重复
        QueryWrapper<User> uqw = new QueryWrapper<>();
        uqw.eq("username", username);
        Long count = userMapper.selectCount(uqw);
        if (count > 0) throw new BusinessException("昵称不符合要求", 50002, "该昵称已存在");

        // 2.对密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        // 3.向数据库中插入用户数据
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEncryptPassword(encryptPassword);
        user.setPhone(phone);
        user.setAvatar("http://ry2s7czdf.hd-bkt.clouddn.com/imgs/avatar/winter_nature_6-wallpaper-2560x1600.jpg");

        boolean save = this.save(user);
        //插入失败
        if (!save) throw new BusinessException(UPDATE_ERROR);

        return user.getUserId();
    }

    /**
     * 用户登录
     *
     * @param username 昵称
     * @param password 密码
     * @param request  登录用户信息
     * @return 脱敏用户信息
     */
    @Override
    public User userLogin(String code, String username, String password, HttpServletRequest request) {
        // 1.校验
        // 1.1.昵称, 密码不能为空
        log.info("code = " + code);
        Code2Session code2Session = getCode2Session(appId, secret, code);

        if (StringUtils.isAnyBlank(username, password)) throw new BusinessException(PARMS_ERROR);

        // 1.2.昵称不小于2位, 不大于10位
        if (username.length() < 2 || username.length() > 10) throw new BusinessException("昵称不符合要求", 50000, "昵称长度不符合要求");

        // 1.3.昵称不包含特殊字符
        String pattern = "^[\\u4e00-\\u9fa5A-Za-z\\d_]+$";
        if (!Pattern.matches(pattern, username)) throw new BusinessException("昵称不符合要求", 50001, "昵称包含特殊字符");

        // 1.4.密码不小于6位, 不大于10位
        if (password.length() < 6 || password.length() > 10) throw new BusinessException("密码不符合要求", 60000, "密码长度不符合要求");

        // 1.5.检验该用户是否注册
        User user = new User();

        user.setUsername(username);
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        user.setEncryptPassword(encryptPassword);

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", username);
        User one = this.getOne(qw);

        // 1.5.1.用户未注册(包含了MP自带的逻辑删除校验)
        if (one == null) throw new BusinessException(NOT_REGISTER, "该账户未注册");

        qw.eq("encrypt_password", encryptPassword);
        one = this.getOne(qw);
        if (one == null) throw new BusinessException("密码不符合要求", 60000, "密码错误");

        // 2.脱敏用户信息
        UserDTO safetyUser = getSafetyUser(one, code2Session);
        // 3.记录用户登录态

        String redisKey = String.format("pic_memories:user:login:%s", code2Session.getSession_key());

        try {
            redisTemplate.opsForValue().set(redisKey, safetyUser, 20, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error("redis set key error", e);
        }

        // 4.返回用户信息
        return safetyUser;
    }

    public Code2Session getCode2Session(String appId, String secret, String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", appId);
        paramMap.put("secret", secret);
        paramMap.put("js_code", code);
        paramMap.put("grant_type", "authorization_code");
        String result = HttpUtil.get(url, paramMap);

        log.info("result = " + result);

        Code2Session session = JSONUtil.toBean(result, Code2Session.class);

        System.out.println("session_key:" + session.getSession_key());
        System.out.println("openid:" + session.getOpenid());

        return session;
    }

    /**
     * 用户注销
     *
     * @param session_key session_key
     * @return 注销成功与否(t / f)
     */
    @Override
    public String userLogout(String session_key) {
        // 1.当前登录用户
        User currentUser = this.getCurrentUser(session_key);

        // 2.是否存在
        if (currentUser == null) {
            throw new BusinessException(NO_AUTH, "无权限");
        }

        // 3.移除session_key
        String redisKey = String.format("pic_memories:user:login:%s", session_key);
        redisTemplate.delete(redisKey);

        return "退出登录成功";
    }

    /**
     * @param user 修改信息
     * @return 修改信息成功
     */
    @Override
    public String userUpdate(UserUpdateRequest user) {
        // 1.当前登录用户
        String session_key = user.getSession_key();
        User currentUser = this.getCurrentUser(session_key);

        // 2.是否一致
        if (!user.getUsername().equals(currentUser.getUsername())) {
            throw new BusinessException(NO_AUTH, "无权限");
        }

        // 3.执行修改
        User updateUser = new User();
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + user.getPassword()).getBytes());
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setEncryptPassword(encryptPassword);

        userMapper.updateById(updateUser);
        return "修改信息成功";
    }


    /**
     * @param session_key token
     * @return 当前登陆用户信息
     */
    @Override
    public User getCurrentUser(String session_key) {
        String redisKey = String.format("pic_memories:user:login:%s", session_key);
        User currentUser = (User) redisTemplate.opsForValue().get(redisKey);

        if (currentUser == null) {
            throw new BusinessException(NO_AUTH, "未识别到登录用户信息");
        }

        // 查询数据库, 获取最新信息, 而非登录时记录的信息
        Long userId = currentUser.getUserId();
        return getById(userId);
    }

    /**
     * 用户登录
     *
     * @param username 昵称
     * @param password 密码
     * @param request  登录用户信息
     * @return 脱敏用户信息
     */
    @Override
    public User adminLogin(String username, String password, HttpServletRequest request) {
        // 1.校验
        // 1.1.昵称, 密码不能为空
        if (StringUtils.isAnyBlank(username, password)) throw new BusinessException(PARMS_ERROR);

        // 1.2.昵称不小于2位, 不大于10位
        if (username.length() < 2 || username.length() > 10) throw new BusinessException("昵称不符合要求", 50000, "昵称长度不符合要求");

        // 1.3.昵称不包含特殊字符
        String pattern = "^[\\u4e00-\\u9fa5A-Za-z\\d_]+$";
        if (!Pattern.matches(pattern, username)) throw new BusinessException("昵称不符合要求", 50001, "昵称包含特殊字符");

        // 1.4.密码不小于6位, 不大于10位
        if (password.length() < 6 || password.length() > 10) throw new BusinessException("密码不符合要求", 60000, "密码长度不符合要求");

        // 1.5.检验该用户是否注册
        User user = new User();

        user.setUsername(username);
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        user.setPassword(encryptPassword);

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", username);
        User one = this.getOne(qw);

        // 1.5.1.用户未注册(包含了MP自带的逻辑删除校验)
        if (one == null) throw new BusinessException(NOT_REGISTER, "该账户未注册");

        qw.eq("password", encryptPassword);
        one = this.getOne(qw);
        if (one == null) throw new BusinessException("密码不符合要求", 60000, "密码错误");

        // 2.脱敏用户信息
        User safetyUser = getSafetyUser(one);
        // 3.记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        // 4.返回用户信息
        return safetyUser;
    }

    /**
     * 在线用户列表(管理员后台)
     *
     * @return 用户列表
     */
    @Override
    public Page<User> getPage() {
        Page<User> page = new Page<>(1, 7);
        QueryWrapper<User> uqw = new QueryWrapper<>();

        return this.page(page, uqw);
    }

    /**
     * 当前用户登录态(管理员后台)
     *
     * @param request
     * @return
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        Long id = currentUser.getUserId();

        // 查询数据库, 获取最新信息, 而非登录时记录的信息
        return getById(id);
    }

    /**
     * 用户信息脱敏
     *
     * @param originUser 原始用户
     * @return 脱敏后的用户
     */
    public UserDTO getSafetyUser(User originUser, Code2Session code2Session) {
        if (originUser == null) return null;

        UserDTO safetyUser = new UserDTO();
        safetyUser.setUserId(originUser.getUserId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setAvatar(originUser.getAvatar());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setCreateTime(originUser.getUpdateTime());
        safetyUser.setIsDelete(originUser.getIsDelete());
        safetyUser.setSession_key(code2Session.getSession_key());
        safetyUser.setOpenid(code2Session.getOpenid());

        return safetyUser;
    }

    /**
     * 用户信息脱敏
     *
     * @param originUser 原始用户
     * @return 脱敏后的用户
     */
    public User getSafetyUser(User originUser) {
        if (originUser == null) return null;

        UserDTO safetyUser = new UserDTO();
        safetyUser.setUserId(originUser.getUserId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setAvatar(originUser.getAvatar());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setCreateTime(originUser.getUpdateTime());
        safetyUser.setIsDelete(originUser.getIsDelete());

        return safetyUser;
    }
}




