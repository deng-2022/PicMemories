package com.memory.picmemories.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.PicMemoriesApplication;
import com.memory.picmemories.model.entity.Wallpaper;
import com.memory.picmemories.model.enums.WallpaperStatusEnum;
import com.memory.picmemories.service.WallpaperService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest(classes = PicMemoriesApplication.class)
@Slf4j
public class WallpaperServiceImplTest {
    @Resource
    private WallpaperService wallpaperService;

    @Test
    Page<Wallpaper> test() {
        Page<Wallpaper> page = new Page<>(1, 7);
        QueryWrapper<Wallpaper> wqw = new QueryWrapper<>();

        wqw.eq("status", WallpaperStatusEnum.PASS.getValue());
        wqw.groupBy("type");
        Page<Wallpaper> wallpaperPage = wallpaperService.page(page, wqw);
// select * from wallpaper
// where status = 1 and
//       is_delete = 0 and
//       group by

        log.info("pages" + wallpaperPage);
        return wallpaperPage;
    }
}