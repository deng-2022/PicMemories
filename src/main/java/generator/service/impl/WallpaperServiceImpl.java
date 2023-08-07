package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Wallpaper;
import generator.service.WallpaperService;
import generator.mapper.WallpaperMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【wallpaper(壁纸信息)】的数据库操作Service实现
* @createDate 2023-08-06 14:40:47
*/
@Service
public class WallpaperServiceImpl extends ServiceImpl<WallpaperMapper, Wallpaper>
    implements WallpaperService{

}




