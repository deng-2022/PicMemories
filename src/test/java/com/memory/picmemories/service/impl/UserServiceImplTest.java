package com.memory.picmemories.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceImplTest {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

    @Test
    void loginTest() {

        String redisKey = "pic_memories:user:login:session_key";


        stringRedisTemplate.opsForValue().set("pic", "memory");

        stringRedisTemplate.opsForValue().set(redisKey, "memory", 20, TimeUnit.HOURS);
        redisTemplate.opsForValue().set(redisKey + "2", "memory2");
    }
}