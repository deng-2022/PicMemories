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
     * 2 - girl
     */
    TECHNOLOGY(2, "girl"),

    /**
     * 3 - 唯美治愈
     */
    STAR(3, "唯美治愈"),

    /**
     * 4 - 游戏竞技
     */
    GAME(4, "游戏竞技"),

    /**
     * 5 - 美食餐饮
     */
    FOOD(5, "美食餐饮"),

    /**
     * 6 - 星空宇宙
     */
    HEALING(6, "星空宇宙"),

    /**
     * 7 - 影音视听
     */
    MOVIE(7, "影音视听"),

    /**
     * 8 - 城市建筑
     */
    CITY(8, "城市建筑"),

    /**
     * 9 - 音乐艺术
     */
    MUSIC(9, "音乐艺术"),

    /**
     * 10 - 动物世界
     */
    GIRL(10, "动物世界"),

    /**
     * 11 - 历史文化
     */
    HISTORY(11, "历史文化");

    /**
     * /**
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