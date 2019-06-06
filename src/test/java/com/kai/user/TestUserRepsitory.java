package com.kai.user;

import com.kai.DynamicDateApplication;
import com.kai.main.Dept;
import com.kai.main.User;
import com.kai.mapper.UserMapper;
import com.kai.repository.DeptRepository;
import com.kai.repository.UserRepository;
import com.kai.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author ggk
 * @date 2019/5/28 0028 上午 10:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DynamicDateApplication.class)
public class TestUserRepsitory {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptService deptService;

    @Test
    public void getUser(){
       List<User> list= userRepository.getUserList();
        System.out.println(list);
    }

    @Test
    public void getDept(){
       List<Dept> list= deptRepository.getDeptList();
        System.out.println(list);
    }
    @Test
    public void getDeptPage(){
        System.out.println(deptRepository.getDeptListPage().getRecords());
    }
    @Test
    public void update(){
        deptRepository.update();
    }

    @Test
    public void insert(){
        Dept dept=new Dept();
        dept.setName("IT部门");
        dept.setRemark("CEO集聚地");
        deptService.add(dept);
    }

}
