package com.example.demo.redis;

import com.example.demo.enity.UserEnity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class redisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setValue() {
        UserEnity userEnity = new UserEnity();
        userEnity.setUserName("chaochao");
        userEnity.setAge(23);
        userEnity.setId(10L);
        redisTemplate.opsForValue().set("student", userEnity);
    }
}
