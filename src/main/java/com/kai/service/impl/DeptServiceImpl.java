package com.kai.service.impl;

import com.kai.common.annotation.DB;
import com.kai.common.base.impl.BaseServiceImpl;
import com.kai.common.datasource.DatabaseType;
import com.kai.main.Dept;
import com.kai.mapper.DeptMapper;
import com.kai.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * @author ggk
 * @date 2019/5/28 0028 下午 4:52
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper,Dept> implements DeptService {
}
