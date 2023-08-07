package com.memory.picmemories.model.enums;

/**
 * @author 邓哈哈
 * 2023/8/06 10:36
 * Function: 壁纸种类枚举
 * Version 1.0
 */
public enum WallpaperTypeEnum {
    /**
     * 0 - 自然风景
     */
    NATURAL(0, "自然风景"),

    /**
     * 1 - 动漫插画
     */
    CARTOON(1, "动漫插画"),

    /**
     * 2 - 动物
     */
    TECHNOLOGY(2, "动物"),

    /**
     * 3 - girl
     */
    GIRL(2, "girl"),

    /**
     * 4 - 游戏
     */
    GAME(2, "游戏"),

    /**
     * 5 - 美食
     */
    FOOD(2, "美食");

    /**

    /**
     * 状态码
     */
    private int value;

    /**
     * 状态描述
     */
    private String text;

    /**
     * 判断壁纸状态
     *
     * @param value 壁纸状态
     * @return 存在与否
     */
    public static WallpaperTypeEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        WallpaperTypeEnum[] values = WallpaperTypeEnum.values();
        for (WallpaperTypeEnum teamStatusEnum : values) {
            if (teamStatusEnum.getValue() == value) {
                return teamStatusEnum;
            }
        }
        return null;
    }

    WallpaperTypeEnum(int value, String text) {
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