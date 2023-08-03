package com.memory.picmemories.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 下载信息
 * @TableName download
 */
@TableName(value ="download")
@Data
public class Download implements Serializable {
    /**
     * 点赞id
     */
    private Long downloadId;

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