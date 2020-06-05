package com.example.demo.listener;

import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
//        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class AyUserListener implements ServletContextListener {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
//    private RedisTemplate redisTemplate;

    @Resource
    private AyUserService ayUserService;
    private static final String ALL_USER = "ALL_USER_LIST";
    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //查询数据库所有的用户
        List<AyUser> ayUserList =  ayUserService.findAll();
        //清除缓存中的用户数据
        redisTemplate.delete(ALL_USER);
        //存放到redis缓存中
//        redisTemplate.opsForList().leftPushAll(ALL_USER, ayUserList);
        for (AyUser ayUser : ayUserList) {
            redisTemplate.opsForList().leftPush(ALL_USER,ayUser);
        }
//        redisTemplate.opsForValue().set(ALL_USER,ayUserList);
        List<Object> queryUserList = redisTemplate.opsForList().range(ALL_USER,0,-1);
//        List<Object> queryUserList = (List<Object>)redisTemplate.opsForValue().get(ALL_USER);

        //真实项目中需要注释掉
//        System.out.println("queryUserList:" + queryUserList);
//        System.out.println("缓存中目前的用户数有：" + queryUserList.size() + " 人");
//        System.out.println("ServletContext上下文初始化");
        logger.info("ServletContext上下文初始化");
        logger.info("缓存中目前的用户数有：" + queryUserList.size() + " 人");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext上下文销毁");
    }
}
