package com.memory.picmemories.mapper;

import com.memory.picmemories.model.entity.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lenovo
* @description 针对表【like(点赞信息)】的数据库操作Mapper
* @createDate 2023-08-02 17:28:27
* @Entity generator.domain.Like
*/
@Mapper
public interface LikeMapper extends BaseMapper<Like> {

}




