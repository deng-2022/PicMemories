package com.memory.picmemories.model.request.wallpaper;

import lombok.Data;

/**
 * @author 邓哈哈
 * 2023/8/15 14:32
 * Function:
 * Version 1.0
 */
@Data
public class WallpaperLikeRequest {
    private String url;
    private String session_key;
    private Integer liked;
}
