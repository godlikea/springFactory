package com.kai;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author ggk
 * @date 2019/5/28 0028 上午 10:10
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DynamicDateApplication {

    public static void  main(String[] args){
        SpringApplication.run(DynamicDateApplication.class);
    }
}
