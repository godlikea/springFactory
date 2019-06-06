package com.kai.main;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ggk
 * @date 2019/5/30 0030 下午 5:40
 */
@Data
public class UserEntity implements Serializable {

    private String userId;

    private String userName;

    private String email;
}
