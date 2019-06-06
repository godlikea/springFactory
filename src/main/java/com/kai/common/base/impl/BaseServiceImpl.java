package com.kai.common.base.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kai.common.annotation.DB;
import com.kai.common.base.BaseService;
import com.kai.common.datasource.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ggk
 * @date 2019/5/28 0028 下午 4:35
 */
public class BaseServiceImpl<M extends BaseMapper,T> implements BaseService<T> {
    @Autowired
    private  M mapper;

    @Override
    public void add(T t) {
        mapper.insert(t);
    }
}
