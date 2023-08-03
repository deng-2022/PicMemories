package com.memory.picmemories.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memory.picmemories.model.Like;
import com.memory.picmemories.service.LikeService;
import com.memory.picmemories.mapper.LikeMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【like(点赞信息)】的数据库操作Service实现
* @createDate 2023-08-02 17:28:27
*/
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like>
    implements LikeService{

}




