package com.memory.picmemories.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.picmemories.common.ErrorCode;
import com.memory.picmemories.exception.BusinessException;
import com.memory.picmemories.mapper.WallpaperMapper;
import com.memory.picmemories.model.entity.Like;
import com.memory.picmemories.model.entity.User;
import com.memory.picmemories.model.entity.Wallpaper;
import com.memory.picmemories.model.enums.WallpaperTypeEnum;
import com.memory.picmemories.service.LikeService;
import com.memory.picmemories.service.UserService;
import com.memory.picmemories.service.WallpaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lenovo
 * @description 针对表【wallpaper(壁纸信息)】的数据库操作Service实现
 * @createDate 2023-08-02 17:28:19
 */
@Service
public class WallpaperServiceImpl extends ServiceImpl<WallpaperMapper, Wallpaper>
        implements WallpaperService {
    @Resource
    private UserService userService;
    @Resource
    private LikeService likeService;
    @Resource
    private WallpaperMapper wallpaperMapper;

    /**
     * 分页查询
     * 分类查询壁纸
     *
     * @return 分类壁纸
     */
    @Override
    public List<Wallpaper> getPageByType(Integer searchType) {
        if (WallpaperTypeEnum.getEnumByValue(searchType) == null) {
            throw new BusinessException(ErrorCode.PARMS_ERROR, "没有这样的壁纸类型");
        }

        QueryWrapper<Wallpaper> type_wqw = new QueryWrapper<>();
        type_wqw.eq("type", searchType);

        return this.list(type_wqw);
    }

    /**
     * 分页查询
     * 分类最新壁纸
     *
     * @return 分类壁纸
     */
    @Override
    public List<Wallpaper> getPageByTime() {
        QueryWrapper<Wallpaper> time_wqw = new QueryWrapper<>();
        time_wqw.orderByAsc("create_time");

        return this.list(time_wqw);
    }

    /**
     * 分页查询
     * 查询精选壁纸
     *
     * @return 分类壁纸
     */
    @Override
    public List<Wallpaper> getPageByLike() {
        QueryWrapper<Wallpaper> like_wqw = new QueryWrapper<>();
        like_wqw.orderByDesc("likes");

        return this.list(like_wqw);
    }

    /**
     * 获取上传用户
     *
     * @param url 壁纸URL
     * @return 用户
     */
    @Override
    public User getUserByUrl(String url) {
        // 1.获取壁纸
        QueryWrapper<Wallpaper> wqw = new QueryWrapper<>();
        wqw.eq("wallpaper_url", url);

        Wallpaper wallpaper = this.getOne(wqw);
        Long userId = wallpaper.getUserId();

        // 2.获取上传用户
        return userService.getById(userId);
    }

    /**
     * 获取上传用户
     *
     * @param url 壁纸URL
     * @return 用户
     */
    @Override
    public List<String> getTagsByUrl(String url) {
        // 1.获取壁纸
        QueryWrapper<Wallpaper> wqw = new QueryWrapper<>();
        wqw.eq("wallpaper_url", url);

        Wallpaper wallpaper = this.getOne(wqw);

        // 2.获取tags
        String tags = wallpaper.getTags();
        // 2.2.去除 []
        String cleanedTags = tags.substring(1, tags.length() - 1);
        // 2.3.String转array, array转List
        List<String> list = Arrays.asList(cleanedTags.split(","));
        System.out.println(list);

        return Arrays.asList(cleanedTags.split(","));
    }


    /**
     * 在线用户列表(管理员后台)
     *
     * @return 用户列表
     */
    @Override
    public Page<Wallpaper> getPage() {
        Page<Wallpaper> page = new Page<>(1, 7);
        QueryWrapper<Wallpaper> uqw = new QueryWrapper<>();

        return this.page(page, uqw);
    }

    /**
     * 点赞壁纸
     *
     * @param url         壁纸url
     * @param session_key session_key
     * @return
     */
    @Override
    public Boolean getWallpaperLike(String url, String session_key, int liked) {
        // 1.检验权限
        User currentUser = userService.getCurrentUser(session_key);
        Long userId = currentUser.getUserId();
        // 2.删除记录
        if (liked == 0) {
            QueryWrapper<Like> lqw = new QueryWrapper<>();
            lqw.eq("wallpaper_url", url);

            boolean remove = likeService.remove(lqw);
            if (!remove)
                throw new BusinessException(ErrorCode.UPDATE_ERROR, "取消收藏失败");
        } else if (liked == 1) {
            // 2.获取wallpaperId
            QueryWrapper<Wallpaper> wqw = new QueryWrapper<>();
            wqw.eq("wallpaper_url", url);

            Wallpaper wallpaper = this.getOne(wqw);
            Long wallpaperId = wallpaper.getWallpaperId();
            // 4.新增记录
            Like like = new Like();
            like.setUserId(userId);
            like.setWallpaperId(wallpaperId);

            boolean save = likeService.save(like);
            if (!save)
                throw new BusinessException(ErrorCode.UPDATE_ERROR, "收藏失败");
        } else {
            throw new BusinessException(ErrorCode.PARMS_ERROR, "请求参数错误");
        }
        return true;
    }
}




