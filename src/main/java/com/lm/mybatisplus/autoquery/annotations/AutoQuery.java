package com.lm.mybatisplus.autoquery.annotations;


import com.baomidou.mybatisplus.annotation.TableField;
import com.lm.mybatisplus.autoquery.constant.AutoJoinType;

import java.lang.annotation.*;


/**
 * @author LM
 * @description:
 * @date 2019/3/12 9:24
 */
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoQuery {

    /**
     * 自动搜索的列(映射到注解当前字段)
     * @return
     */
    String autoColumn();

    /**
     * 外键表被连接字段（默认为 id）
     * @return
     */
    String foreignJoinColumn() default "id";

    /**
     * 主表的外键字段
     * @return
     */
    String foreignKey();

    /**
     * 关联的外键表
     * @return
     */
    String foreignTable();

    /**
     * 连表的类型，默认为 left join
     * @return
     */
    String joinType() default AutoJoinType.LEFT_JOIN_TYPE;

    /**
     * 说明：除了需要查找包含AutoQuery的字段，还需要查询当前DO下其他字段时
     * 使用该属性，值为额外查询字段的数据库列名
     * @return
     */
    String[] extraColumns() default {};

}
