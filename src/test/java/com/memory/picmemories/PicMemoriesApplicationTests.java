package com.memory.picmemories;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memory.picmemories.model.entity.Wallpaper;
import com.memory.picmemories.model.enums.WallpaperStatusEnum;
import com.memory.picmemories.service.WallpaperService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class PicMemoriesApplicationTests {
    @Resource
    private WallpaperService wallpaperService;


    @Test
    void contextLoads() {
        System.out.println(WallpaperStatusEnum.PASS.getValue());
    }

    @Test
    void test() {
        // 1.查询所有分类
        QueryWrapper<Wallpaper> wqw = new QueryWrapper<>();
        wqw.select("type");
        wqw.eq("status", WallpaperStatusEnum.PASS.getValue());
        wqw.groupBy("type");
        List<Wallpaper> list = wallpaperService.list(wqw);
        // 2.存储所有分类
        ArrayList<Integer> tyeList = new ArrayList<>();
        for (Wallpaper wallpaper : list) {
            Integer type = wallpaper.getType();
            tyeList.add(type);
        }
        // 3.按分类查询, 返回壁纸列表
        List<Wallpaper> wallpaperListByType = new ArrayList<>();
        for (Integer type : tyeList) {
            QueryWrapper<Wallpaper> type_wqw = new QueryWrapper<>();
            type_wqw.eq("type", type);

            wallpaperListByType = wallpaperService.list(type_wqw);
        }
//        return wallpaperListByType;
    }

}
