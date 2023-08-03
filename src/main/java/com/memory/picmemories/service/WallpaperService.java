package com.memory.picmemories.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.model.Wallpaper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Lenovo
* @description 针对表【wallpaper(壁纸信息)】的数据库操作Service
* @createDate 2023-08-02 17:28:19
*/
public interface WallpaperService extends IService<Wallpaper> {
    Page<Wallpaper> getPage();

}
