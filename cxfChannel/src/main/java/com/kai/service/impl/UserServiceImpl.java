package com.kai.service.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kai.main.UserEntity;
import com.kai.service.UserService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ggk
 * @date 2019/5/30 0030 下午 5:46
 */
@WebService(name = "UserService",serviceName ="UserService",
            targetNamespace = "http://service.kai.com",
            endpointInterface = "com.kai.service.UserService")
@Service
public class UserServiceImpl implements UserService {

    private Map<String,UserEntity> map=new HashMap<String,UserEntity>();

    public  UserServiceImpl(){
        System.out.println("向实体类插入数据");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString().replace("-", ""));
        userEntity.setUserName("test1");
        userEntity.setEmail("maplefix@163.xom");
        map.put(userEntity.getUserId(), userEntity);

        userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString().replace("-", ""));
        userEntity.setUserName("test2");
        userEntity.setEmail("maplefix@163.xom");
        map.put(userEntity.getUserId(), userEntity);

        userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString().replace("-", ""));
        userEntity.setUserName("test3");
        userEntity.setEmail("maplefix@163.xom");
        map.put(userEntity.getUserId(), userEntity);
    }

    public UserEntity getUser(String userId) {
        return map.get(userId);
    }


    public String getUserName(String userId) {
        return "userId为：" + userId;
    }
}
