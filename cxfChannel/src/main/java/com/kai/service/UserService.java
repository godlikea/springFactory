package com.kai.service;

import com.kai.main.UserEntity;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author ggk
 * @date 2019/5/30 0030 下午 5:42
 */
@WebService(name = "UserService",
        targetNamespace = "http://service.kai.com")
public interface UserService {

    @WebMethod
    UserEntity getUser(@WebParam(name = "userId")String userId);

    @WebMethod
    @WebResult(name = "String",targetNamespace = "")
    String getUserName(@WebParam(name = "userId")String userId);
}
