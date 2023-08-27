use pic_memories;
-- 用户信息
drop table if exists pic_memories.user;
create table if not exists pic_memories.`user`
(
    `user_id`   bigint auto_increment primary key comment '用户id',
    `username`  varchar(128)                         not null comment '用户名',
    `password`  varchar(32)                          not null comment '用户名',
    `avatar`    varchar(256)                         not null comment '头像',
    phone       varchar(16)                          null comment '电话',
    user_role   tinyint(1) default 0                 null comment '普通用户-0 管理员-1---',
    createTime  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime  datetime   default CURRENT_TIMESTAMP null comment '更新时间',
    `is_delete` tinyint(1) default 0                 not null comment '是否删除'
) comment '用户信息';

-- 壁纸信息
drop table if exists pic_memories.wallpaper;
create table if not exists `wallpaper`
(
    `wallpaper_id`          bigint auto_increment primary key comment '壁纸id',
    `wallpaper_name`        varchar(256)                         not null comment '壁纸名',
    `wallpaper_description` varchar(256)                         not null comment '壁纸描述',
    `wallpaper_url`         varchar(256)                         not null comment '壁纸URL',
    `upload_date`           datetime                             not null comment '上传日期',
    `likes`                 int                                  not null comment '点赞数',
    `downloads`             int                                  not null comment '用户名',
    `user_id`               bigint                               not null comment '所属用户',
    status                  tinyint(1)                           not null default 1 comment '1 - 审核中 2 - 已发布 3 - 未通过',
    `create_time`           datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`           datetime   default CURRENT_TIMESTAMP not null comment '更新时间',
    `is_delete`             tinyint(1) default 0                 not null comment '是否删除'
) comment '壁纸信息';

-- 上传信息
drop table if exists pic_memories.upload;
create table upload
(
    id            bigint auto_increment comment 'id'
        primary key,
    user_id       bigint                               not null comment '上传者',
    wallpaper_id  bigint                               not null comment '上传壁纸',
    `create_time` datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime   default CURRENT_TIMESTAMP not null comment '更新时间',
    `is_delete`   tinyint(1) default 0                 not null comment '是否删除'
)
    comment '上传信息';

-- 点赞信息
drop table if exists `like`;
create table `like`
(
    id           bigint auto_increment primary key comment '点赞id',
    user_id      bigint                                 not null comment '点赞用户id',
    wallpaper_id bigint                                 not null comment '被点赞的壁纸id',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime     default CURRENT_TIMESTAMP not null comment '更新时间',
    is_delete    varchar(256) default '0'               not null comment '是否删除'
)
    comment '点赞信息';

-- 下载信息
create table if not exists pic_memories.`download`
(
    `download_id`
        bigint
                                 not
                                     null
        comment
            '点赞id',
    `user_id`
        bigint
                                 not
                                     null
        comment
            '点赞用户id',
    `wallpaper_id`
        bigint
                                 not
                                     null
        comment
            '被点赞的壁纸id',
    `like_date`
        datetime
                                 not
                                     null
        comment
            '点赞时间',
    `create_time`
        datetime
                     default
                         CURRENT_TIMESTAMP
                                 not
                                     null
        comment
            '创建时间',
    `update_time`
        datetime
                     default
                         CURRENT_TIMESTAMP
                                 not
                                     null
        comment
            '更新时间',
    `is_delete`
        varchar(256) default '0' not null comment '是否删除'
) comment '下载信息';