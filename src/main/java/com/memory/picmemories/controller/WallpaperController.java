package com.memory.picmemories.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.common.BaseResponse;
import com.memory.picmemories.common.ResultUtils;
import com.memory.picmemories.model.Wallpaper;
import com.memory.picmemories.service.WallpaperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @GetMapping("/listPage")
    public BaseResponse<Page<Wallpaper>> getPage() {
        Page<Wallpaper> result = wallpaperService.getPage();
        return ResultUtils.success(result);
    }
}
