package com.fenglai.test.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-27
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 类似使用Jackson序列化, 用来序列化value
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // String序列化用来序列化key
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();

        // string key序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // string value序列化
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        // hash key序列化
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // hash value序列化
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        return redisTemplate;
    }
}
