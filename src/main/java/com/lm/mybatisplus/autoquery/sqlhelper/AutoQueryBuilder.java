package com.lm.mybatisplus.autoquery.sqlhelper;

import com.lm.mybatisplus.autoquery.enums.AutoExceptionEnum;
import com.lm.mybatisplus.autoquery.exception.AutoQueryCheckException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @author LM
 * @description: 自动查询建造者
 * @date 2019/3/12 12:06
 */
@Data
@Slf4j
public class AutoQueryBuilder {

    private List<AutoQueryMeta> autoQueryMetaList;

    private String completeSelect;
    private String completeTable;
    private String whereSql;

    public AutoQueryBuilder(List<AutoQueryMeta> autoQueryMetaList) {
        this.autoQueryMetaList = autoQueryMetaList;
    }

    /**
     * 构建完整的SQL语句
     * @param oriSelectColumn
     * @param mainTableName
     * @return
     */
    public String buildCompleteSql(String oriSelectColumn, String mainTableName) {

        String sql = MySqlMethod.AUTO_QUERY.getSql();

        String buildSelectColumn = buildSelectColumn(oriSelectColumn);

        if (StringUtils.isEmpty(buildSelectColumn)) {
            //说明该实体没有AutoQuery注解, 然而还进行调用了autoQuery方法
            throw new AutoQueryCheckException(AutoExceptionEnum.AUTO_QUERY_NOT);
        }

        String buildAfterFromSql = buildAfterFromSql(mainTableName);

        //最后两个字符占位为where语句, 以及逻辑删除
        return String.format(sql, buildSelectColumn, buildAfterFromSql, "%s");

    }

    /**
     * 构建select语句
     * @param oriSelectColumn
     * @return
     */
    public String buildSelectColumn(String oriSelectColumn) {

        StringBuilder oriSelectBuilder = new StringBuilder();

        String buildSelectColumn = null;
        int size = autoQueryMetaList.size();

        String oriSelectColumnWithForign = oriSelectColumn;
        oriSelectBuilder.append(oriSelectColumnWithForign);

        for (int metaIndex = 0; metaIndex < size; metaIndex++) {
            AutoQueryMeta autoQueryMeta = autoQueryMetaList.get(metaIndex);
            String autoColumn = autoQueryMeta.getAutoColumn();
            String[] extraColumns = autoQueryMeta.getExtraColumns();

            //添加额外查询列，以及外键查询列
            if (metaIndex == 0) {
                buildSelectColumn = AutoQueryHelper.buildSelectColumn(oriSelectColumn, autoColumn, extraColumns, metaIndex, true);
            } else {
                buildSelectColumn = AutoQueryHelper.buildSelectColumn(oriSelectColumn, autoColumn, extraColumns, metaIndex, false);
            }
            oriSelectColumn = buildSelectColumn;
        }
        return buildSelectColumn;
    }

    public String buildAfterFromSql(String mainTableName) {
         return AutoQueryHelper.buildTableByMeta(autoQueryMetaList, mainTableName);
    }

    /**
     * where语句用querywrapper自动构建
     * @return
     */
    public String buildWhereSql() {
        return null;
    }




}
