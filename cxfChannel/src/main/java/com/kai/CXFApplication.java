package com.kai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ggk
 * @date 2019/5/30 0030 下午 4:00
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CXFApplication {

    public static void main(String[] args){
        SpringApplication.run(CXFApplication.class);
    }
}
