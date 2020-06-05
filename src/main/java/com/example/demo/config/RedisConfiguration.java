package com.example.demo.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfiguration {
    // 这里工厂配置报错，不理会，注释掉
//    @Bean("jsonRedisCache")
//    public CacheManager cacheManager(@Autowired RedisTemplate<String, Object> redisTemplate) {
//        return new RedisCacheManager(redisTemplate);
//    }
//这个不知道干啥的，key生成器
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }

    // 给redis配置一个value解析器，本源码抄自网页，可以通过运行
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(@Autowired RedisConnectionFactory cf) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer());
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(cf);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
    //个性化申明jackson2JsonRedisSerializer，此处可选
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    @Bean
//    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
//        final Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(
//                Object.class);
//        final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
//        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.setSerializationInclusion(Include.NON_NULL);
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        return jackson2JsonRedisSerializer;
//    }

//    @Autowired(required = false)
    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Autowired RedisConnectionFactory cf) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // java对象可采用hashmap键值对的形式存储，也可以采用序列化方式存储,json是hashmap的方式，默认是序列化
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setConnectionFactory(cf);

//        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        redisTemplate.setHashValueSerializer(stringSerializer);
//        this.redisTemplate = redisTemplate;
        return redisTemplate;
    }
}