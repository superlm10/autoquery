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
@TableName("student")
public class Student {

    private Long id;

    private String stuName;

    @AutoQuery(autoColumn = "class_name", foreignJoinColumn = "class_name", foreignKey = "class_name", foreignTable = "school_class", extraColumns = {"school_id"})
    @TableField(exist = false)
    private String className;

    @AutoQuery(autoColumn = "school_name", foreignKey = "school_id", foreignTable = "school", joinType = AutoJoinType.JOIN_TYPE)
    @TableField(exist = false)
    private String schoolName;


    private Long schoolId;


    private Integer stuAge;

}
