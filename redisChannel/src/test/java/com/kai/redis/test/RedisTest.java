package com.kai.redis.test;

import com.kai.redis.RedisApplication;
import com.kai.redis.common.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ggk
 * @date 2019/5/30 0030 下午 3:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testAdd(){
        redisUtil.set("a",13);
    }

    @Test
    public void testGet(){
        Object o=redisUtil.get("a");
        System.out.println(o);
    }
    @Test
    public void testGet1(){
        redisUtil.hset("b","a",123);
    }
    @Test
    public void testAdd1(){
        System.out.println( redisUtil.hget("b","a"));
    }
}
