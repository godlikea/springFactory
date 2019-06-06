package com.kai.repository;

import com.kai.common.datasource.DataSourceContextHolder;
import com.kai.common.datasource.DatabaseType;
import com.kai.main.User;
import com.kai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ggk
 * @date 2019/5/28 0028 上午 9:57
 */
@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
       // DataSourceContextHolder.setDatabaseType(DatabaseType.onetest);
        return userMapper.selectList(null);
    }
}
