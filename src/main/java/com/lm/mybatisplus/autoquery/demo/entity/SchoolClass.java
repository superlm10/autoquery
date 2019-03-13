package com.lm.mybatisplus.autoquery.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LM
 * @description:
 * @date 2019/3/11 15:34
 */
@Data
@TableName("school_class")
public class SchoolClass {

    private String className;

    private Long id;

}
