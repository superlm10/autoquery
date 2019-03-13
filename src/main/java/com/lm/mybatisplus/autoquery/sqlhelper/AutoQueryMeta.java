package com.lm.mybatisplus.autoquery.sqlhelper;

import lombok.Data;

/**
 * @author LM
 * @description: AutoQuery元数据, 与注解属性对应 {@link com.lm.mybatisplus.autoquery.annotations.AutoQuery}
 * @date 2019/3/12 11:09
 */
@Data
public class AutoQueryMeta {

    private String autoColumn;

    private String relationColumn;

    private String foreignKey;

    private String foreignTable;

    private String joinType;

    private String[] extraColumns;


    public AutoQueryMeta(String autoColumn, String relationColumn, String foreignKey, String foreignTable, String joinType, String[] extraColumns) {
        this.autoColumn = autoColumn;
        this.relationColumn = relationColumn;
        this.foreignKey = foreignKey;
        this.foreignTable = foreignTable;
        this.joinType = joinType;
        this.extraColumns = extraColumns;
    }

    /**
     * 获取拼接模版
     * @return
     */
    private String getCondition() {

        return null;
    }
}
