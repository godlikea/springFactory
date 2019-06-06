package com.kai.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kai.common.annotation.DB;
import com.kai.common.datasource.DataSourceContextHolder;
import com.kai.common.datasource.DatabaseType;
import com.kai.common.datasource.DynamicDataSource;
import com.kai.main.Dept;
import com.kai.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ggk
 * @date 2019/5/28 0028 下午 2:19
 */
@Repository
public class DeptRepository {

    @Autowired
    private DeptMapper deptMapper;

    @DB(DatabaseType.twotest)
    public List<Dept>  getDeptList(){
        return deptMapper.selectList(null);
    }


    @DB(DatabaseType.twotest)
    public IPage<Dept> getDeptListPage(){
        IPage<Dept> page=new Page<>(1,1);
        IPage<Dept> pageData= deptMapper.selectPage(page,null);
        return pageData;
    }

    @DB(DatabaseType.twotest)
    public void update(){
        Dept d=new Dept();
        d.setId(1);
        d.setName("工程部门的");
        deptMapper.updateById(d);
    }
}
