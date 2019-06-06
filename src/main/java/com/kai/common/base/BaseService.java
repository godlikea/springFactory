package com.kai.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author ggk
 * @date 2019/5/28 0028 下午 4:33
 */
public interface BaseService<T> {

    void add(T t);
}
