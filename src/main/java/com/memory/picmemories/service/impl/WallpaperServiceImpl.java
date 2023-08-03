package com.memory.picmemories.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.picmemories.model.Wallpaper;
import com.memory.picmemories.service.WallpaperService;
import com.memory.picmemories.mapper.WallpaperMapper;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 * @description 针对表【wallpaper(壁纸信息)】的数据库操作Service实现
 * @createDate 2023-08-02 17:28:19
 */
@Service
public class WallpaperServiceImpl extends ServiceImpl<WallpaperMapper, Wallpaper>
        implements WallpaperService {

    @Override
    public Page<Wallpaper> getPage() {
        Page<Wallpaper> page = new Page<>(1, 7);
        QueryWrapper<Wallpaper> wqw = new QueryWrapper<>();

        return this.page(page, wqw);
    }
}




