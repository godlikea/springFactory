package com.kai.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ggk
 * @date 2019/5/29 0029 下午 5:59
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RedisApplication {

    public static void main(String[] args){
        SpringApplication.run(RedisApplication.class);
    }
}
