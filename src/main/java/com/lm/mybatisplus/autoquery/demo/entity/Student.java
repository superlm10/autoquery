package com.lm.mybatisplus.autoquery.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lm.mybatisplus.autoquery.annotations.AutoQuery;
import com.lm.mybatisplus.autoquery.constant.AutoJoinType;
import lombok.Data;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 15:51
 */
@Data
public class Student {

    private Long id;

    private String stuName;


    private Long schoolId;


    private Integer stuAge;

}
