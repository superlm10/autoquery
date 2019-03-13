package com.lm.mybatisplus.autoquery.demo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lm.mybatisplus.autoquery.annotations.AutoQuery;
import com.lm.mybatisplus.autoquery.constant.AutoJoinType;
import com.lm.mybatisplus.autoquery.demo.entity.Student;
import lombok.Data;
import lombok.ToString;

/**
 * TableName 需要与实体表名一致
 * @author LM
 * @description: 如果不想在Entity写与表结构无关的字段, 实际场景：vo类返回数据给前端页面
 * @date 2019/3/13 11:47
 */
@Data
@ToString(callSuper = true)
@TableName("student")
public class StudentVo extends Student {

    @AutoQuery(autoColumn = "class_name", foreignJoinColumn = "class_name", foreignKey = "class_name", foreignTable = "school_class", extraColumns = {"school_id"})
    @TableField(exist = false)
    private String className;

    @AutoQuery(autoColumn = "school_name", foreignKey = "school_id", foreignTable = "school", joinType = AutoJoinType.JOIN_TYPE)
    @TableField(exist = false)
    private String schoolName;


}
