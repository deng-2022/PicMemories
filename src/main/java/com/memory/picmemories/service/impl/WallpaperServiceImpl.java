package com.memory.picmemories.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.picmemories.common.ErrorCode;
import com.memory.picmemories.exception.BusinessException;
import com.memory.picmemories.model.entity.Wallpaper;
import com.memory.picmemories.model.enums.WallpaperStatusEnum;
import com.memory.picmemories.model.enums.WallpaperTypeEnum;
import com.memory.picmemories.service.WallpaperService;
import com.memory.picmemories.mapper.WallpaperMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lenovo
 * @description 针对表【wallpaper(壁纸信息)】的数据库操作Service实现
 * @createDate 2023-08-02 17:28:19
 */
@Service
public class WallpaperServiceImpl extends ServiceImpl<WallpaperMapper, Wallpaper>
        implements WallpaperService {
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
}




