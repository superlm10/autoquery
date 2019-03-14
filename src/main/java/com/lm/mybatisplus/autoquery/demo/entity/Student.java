package com.lm.mybatisplus.autoquery.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName(value = "student")
public class Student {

    private Long id;

    private String stuName;

    /**
     * 注解的autoColumn的行为以mybatis配置为主,如果配置了驼峰转化，那么class_name会自动映射到className， 其他同理
     */
    @AutoQuery(autoColumn = "class_name", foreignJoinColumn = "class_name", foreignKey = "class_name", foreignTable = "school_class", extraColumns = {"school_id"})
    @TableField(exist = false)
    private String className;

    private Long schoolId;

    @AutoQuery(autoColumn = "school_name", foreignKey = "school_id", foreignTable = "school", joinType = AutoJoinType.JOIN_TYPE)
    @TableField(exist = false)
    private String schoolName;

    private Integer stuAge;

    @TableLogic
    private Integer deleted;

}
