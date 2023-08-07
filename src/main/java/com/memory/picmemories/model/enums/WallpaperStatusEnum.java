package com.memory.picmemories.model.enums;

/**
 * @author 邓哈哈
 * 2023/8/06 10:36
 * Function: 壁纸状态枚举
 * Version 1.0
 */
public enum WallpaperStatusEnum {
    /**
     * 0 - 审核中, 壁纸正在被管理员审核中
     */
    REVIEW(0, "审核中"),

    /**
     * 1 - 已发布, 壁纸能够被展示
     */
    PASS(1, "已发布"),

    /**
     * 2 - 不通过, 壁纸不允许展示
     */
    NOPASS(2, "不通过");

    /**
     * 状态码
     */
    private int value;

    /**
     * 状态描述
     */
    private String text;

    /**
     * 判断用户状态
     *
     * @param value 用户状态
     * @return 存在与否
     */
    public static WallpaperStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        WallpaperStatusEnum[] values = WallpaperStatusEnum.values();
        for (WallpaperStatusEnum teamStatusEnum : values) {
            if (teamStatusEnum.getValue() == value) {
                return teamStatusEnum;
            }
        }
        return null;
    }

    WallpaperStatusEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}