package com.memory.picmemories.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 壁纸信息
 * @TableName wallpaper
 */
@TableName(value ="wallpaper")
@Data
public class Wallpaper implements Serializable {
    /**
     * 壁纸id
     */
    private Long wallpaperId;

    /**
     * 壁纸名
     */
    private String wallpaperName;

    /**
     * 壁纸描述
     */
    private String wallpaperDescription;

    /**
     * 壁纸URL
     */
    private String wallpaperUrl;

    /**
     * 上传日期
     */
    private Date uploadDate;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 用户名
     */
    private Integer downloads;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private String isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}