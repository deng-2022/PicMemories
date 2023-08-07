package com.memory.picmemories.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.common.BaseResponse;
import com.memory.picmemories.common.ResultUtils;
import com.memory.picmemories.exception.BusinessException;
import com.memory.picmemories.model.entity.Wallpaper;
import com.memory.picmemories.service.WallpaperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
