package com.sparta.followfollowmeproject.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public RedisUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value, long expirationTimeSeconds) {
        redisTemplate.opsForValue().set(key, (String) value, expirationTimeSeconds, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    // 블랙리스트에 토큰 추가
    public void setBlackList(String key, Object value, long expirationTimeSeconds) {
        redisTemplate.opsForValue().set(key, (String) value, expirationTimeSeconds, TimeUnit.SECONDS);
    }

    // 블랙리스트에 토큰 존재 여부 확인
    public boolean hasKeyBlackList(String key) {
        return redisTemplate.hasKey(key);
    }
}

