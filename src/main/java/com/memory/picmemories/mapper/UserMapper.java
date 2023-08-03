package com.memory.picmemories.mapper;

import com.memory.picmemories.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lenovo
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-08-02 17:28:04
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




