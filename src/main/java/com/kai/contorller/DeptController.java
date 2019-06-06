package com.kai.contorller;

import com.kai.common.annotation.DB;
import com.kai.common.datasource.DatabaseType;
import com.kai.main.Dept;
import com.kai.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ggk
 * @date 2019/5/28 0028 下午 5:57
 */
@RestController
@RequestMapping("/dept")
@DB(DatabaseType.twotest)
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/add")
    public Object twoinsert(Dept dept) {
        deptService.add(dept);
        return "Test";
    }
}
