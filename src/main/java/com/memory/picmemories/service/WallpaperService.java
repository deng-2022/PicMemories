package com.memory.picmemories.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.model.entity.Wallpaper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【wallpaper(壁纸信息)】的数据库操作Service
* @createDate 2023-08-02 17:28:19
*/
public interface WallpaperService extends IService<Wallpaper> {
    List<Wallpaper> getPageByType(Integer searchType);
    List<Wallpaper> getPageByTime();
    List<Wallpaper> getPageByLike();
}
