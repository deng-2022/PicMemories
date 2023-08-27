package com.memory.picmemories.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    @TableId(type = IdType.ASSIGN_ID)
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
     * 所属类型
     */
    private Integer type;

    /**
     * 用户名
     */
    private Integer downloads;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 标签
     */
    private String tags;

    /**
     * 1 - 审核中 2 - 已发布 3 - 未通过
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}