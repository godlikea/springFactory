package com.kai.contorller;

import com.kai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ggk
 * @date 2019/5/28 0028 上午 10:33
 */
@RestController
@RequestMapping("/user")
public class UserContorller {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/list")
    public Object getUserList(){
        return userRepository.getUserList();
    }
}
