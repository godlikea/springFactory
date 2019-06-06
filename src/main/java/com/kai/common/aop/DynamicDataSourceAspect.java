package com.kai.common.aop;

import com.kai.common.annotation.DB;
import com.kai.common.datasource.DataSourceContextHolder;
import com.kai.common.datasource.DatabaseType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ggk
 * @date 2019/5/28 0028 下午 2:39
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

    @Before("execution(* com.kai.contorller.*.*(..))")
    public void beforeSwitchDB(JoinPoint point){
        Class<?> clazz=point.getTarget().getClass();
        String methodName=point.getSignature().getName();
        Class[] argClass=((MethodSignature)point.getSignature()).getParameterTypes();
        try{
           Method method=clazz.getMethod(methodName,argClass);
           DB annotation=clazz.getAnnotation(DB.class);
           DataSourceContextHolder.setDatabaseType(annotation.value());
           log.info(method.getName());
           /*if(StringUtils.contains(method.getName(),"two")){
               DataSourceContextHolder.setDatabaseType(DatabaseType.twotest);
           }*/
           //TODO ggk 修改其实现方式
          /* if(method.isAnnotationPresent(DB.class)){
              DB annotation=method.getAnnotation(DB.class);
              DataSourceContextHolder.setDatabaseType(annotation.value());
           }*/
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }

    @After("execution(* com.kai.contorller.*.*(..))")
    public void AfterSwitchDB(JoinPoint point){
        DataSourceContextHolder.clearDataBase();
    }
}
