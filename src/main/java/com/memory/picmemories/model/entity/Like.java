package com.memory.picmemories.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 点赞信息
 * @TableName like
 */
@TableName(value ="like")
@Data
public class Like implements Serializable {
    /**
     * 点赞id
     */
    private Long likeId;

    /**
     * 点赞用户id
     */
    private Long userId;

    /**
     * 被点赞的壁纸id
     */
    private Long wallpaperId;

    /**
     * 点赞时间
     */
    private Date likeDate;

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
    private String isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}