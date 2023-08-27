package com.memory.picmemories.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.common.BaseResponse;
import com.memory.picmemories.common.ResultUtils;
import com.memory.picmemories.exception.BusinessException;
import com.memory.picmemories.model.entity.User;
import com.memory.picmemories.model.entity.Wallpaper;
import com.memory.picmemories.model.request.wallpaper.WallpaperLikeRequest;
import com.memory.picmemories.service.WallpaperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.memory.picmemories.common.ErrorCode.PARMS_ERROR;

/**
 * @author 邓哈哈
 * 2023/8/2 17:40
 * Function:
 * Version 1.0
 */
@RestController
@RequestMapping("/wallpaper")
public class WallpaperController {
    @Resource
    private WallpaperService wallpaperService;

    /**
     * 分类查询壁纸
     *
     * @param searchType 分类
     * @return 壁纸
     */
    @GetMapping("/listPage/type")
    public BaseResponse<List<Wallpaper>> getPageByType(@RequestParam Integer searchType) {
        //controller对参数的校验
        if (searchType == null) {
            throw new BusinessException(PARMS_ERROR, "请求参数错误");
        }

        List<Wallpaper> result = wallpaperService.getPageByType(searchType);
        return ResultUtils.success(result);
    }

    /**
     * 查询最新壁纸
     *
     * @param
     * @return 壁纸
     */
    @GetMapping("/listPage/time")
    public BaseResponse<List<Wallpaper>> getPageByTime() {
        //controller对参数的校验

        List<Wallpaper> result = wallpaperService.getPageByTime();
        return ResultUtils.success(result);
    }

    /**
     * 查询热门壁纸
     *
     * @param
     * @return 壁纸
     */
    @GetMapping("/listPage/like")
    public BaseResponse<List<Wallpaper>> getPageByLike() {
        //controller对参数的校验

        List<Wallpaper> result = wallpaperService.getPageByLike();
        return ResultUtils.success(result);
    }

    /**
     * 获取上传用户
     *
     * @param url 壁纸url
     * @return 用户
     */
    @GetMapping("/user")
    public BaseResponse<User> getUserByUrl(String url) {
        //controller对参数的校验
        if (StringUtils.isAnyBlank(url)) {
            throw new BusinessException(PARMS_ERROR, "请求参数错误");
        }

        User userByUrl = wallpaperService.getUserByUrl(url);
        return ResultUtils.success(userByUrl);
    }

    /**
     * 点赞壁纸
     *
     * @param likeRequest 壁纸url
     * @return 用户
     */
    @PostMapping("/like")
    public BaseResponse<Boolean> getWallpaperLike(@RequestBody WallpaperLikeRequest likeRequest) {
        //controller对参数的校验
        String session_key = likeRequest.getSession_key();
        String url = likeRequest.getUrl();
        Integer liked = likeRequest.getLiked();
        if (StringUtils.isAnyBlank(url, session_key) || liked == null) {
            throw new BusinessException(PARMS_ERROR, "请求参数错误");
        }

        Boolean result = wallpaperService.getWallpaperLike(url, session_key, liked);
        return ResultUtils.success(result);
    }

    /**
     * 获取壁纸标签
     *
     * @param url 壁纸url
     * @return 用户
     */
    @GetMapping("/tags")
    public BaseResponse<List<String>> getTagsByUrl(String url) {
        //controller对参数的校验
        if (StringUtils.isAnyBlank(url)) {
            throw new BusinessException(PARMS_ERROR, "请求参数错误");
        }

        List<String> tags = wallpaperService.getTagsByUrl(url);
        System.out.println(" " + tags);
        return ResultUtils.success(tags);
    }

    /**
     * 在线壁纸列表(管理员后台)
     *
     * @return
     */
    @GetMapping("/listPage")
    public BaseResponse<Page<Wallpaper>> getPage() {
        Page<Wallpaper> result = wallpaperService.getPage();
        return ResultUtils.success(result);
    }

}
