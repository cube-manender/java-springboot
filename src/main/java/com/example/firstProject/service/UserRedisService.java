package com.example.firstProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserRedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return key + " Added Successfully.";
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}