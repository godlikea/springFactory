package com.kai.main;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ggk
 * @date 2019/5/28 0028 下午 2:08
 */
@TableName("t_dept")
@Data
public class Dept implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String remark;
}
