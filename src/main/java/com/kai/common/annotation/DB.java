package com.kai.common.annotation;

import com.kai.common.datasource.DatabaseType;

import java.lang.annotation.*;

/**
 * @author ggk
 * @date 2019/5/28 0028 下午 2:31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
public @interface DB {
    DatabaseType value() default DatabaseType.onetest;
}
