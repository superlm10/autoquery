package com.lm.mybatisplus.autoquery.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.baomidou.mybatisplus.extension.injector.AbstractLogicMethod;
import com.lm.mybatisplus.autoquery.annotations.AutoQuery;
import com.lm.mybatisplus.autoquery.sqlhelper.AutoQueryBuilder;
import com.lm.mybatisplus.autoquery.sqlhelper.AutoQueryHelper;
import com.lm.mybatisplus.autoquery.sqlhelper.AutoQueryMeta;
import com.lm.mybatisplus.autoquery.sqlhelper.MySqlMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LM
 * @description:
 * @date 2019/3/11 15:41
 */
public class AutoQueryMethod extends AbstractMethod {

    /**
     * sql注入步骤为:
     *
     * 1.获取modelClass注解信息
     * 2.解析为自定义元数据信息
     * 3.构建语句建造者(autoQueryBuilder)
     *
     *   (1)将原始select语句拼接为带有前缀的列名
     *   (2)将from语句后面拼接为连表
     *   (3)where语句使用wrapper自动拼装，注意:wrapper在传入之前已经通过反射进行了处理
     *
     * @param mapperClass
     * @param modelClass
     * @param tableInfo
     * @return
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {

        List<AutoQuery> autoQueries = findFieldWithAutoQuery(modelClass);
        List<AutoQueryMeta> autoQueryMetas = analysisAutoQuery(autoQueries);

        String oriSelectColumn = sqlSelectColumns(tableInfo, false);
        String mainTableName = tableInfo.getTableName();

        AutoQueryBuilder autoQueryBuilder = new AutoQueryBuilder(autoQueryMetas);
        String completeSqlNotWhere = autoQueryBuilder.buildCompleteSql(oriSelectColumn, mainTableName);

        String completeSql = getCompleteSql(completeSqlNotWhere, tableInfo, autoQueryBuilder);
        String method = MySqlMethod.AUTO_QUERY.getMethod();

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, completeSql, modelClass);
        return this.addSelectMappedStatement(mapperClass, method, sqlSource, modelClass, tableInfo);
    }


    /**
     * 获取完整的sql语句
     * @param completeSqlNotWhere
     * @param tableInfo
     * @return
     */
    private String getCompleteSql(String completeSqlNotWhere, TableInfo tableInfo, AutoQueryBuilder autoQueryBuilder) {

        String whereEntityWrapper = mySqlWhereEntityWrapper(true, tableInfo, autoQueryBuilder);
        return String.format(completeSqlNotWhere, whereEntityWrapper);
    }


    /**
     * 查找带有AutoQuery的列名
     * @param modelClass
     * @return
     */
    private List<AutoQuery> findFieldWithAutoQuery(Class<?> modelClass) {
        Field[] fields = modelClass.getDeclaredFields();

        List<AutoQuery> autoQueryList = new ArrayList<>();

        for (Field field :fields) {
            AutoQuery[] autoQueries = field.getAnnotationsByType(AutoQuery.class);
            if (autoQueries != null && autoQueries.length > 0) {
                autoQueryList.add(autoQueries[0]);
            }
        }

        return autoQueryList;
    }


    /**
     * 注解分析为AutoQuery元数据
     * @param autoQueryList
     * @return
     */
    public List<AutoQueryMeta> analysisAutoQuery(List<AutoQuery> autoQueryList) {

        List<AutoQueryMeta> autoQueryMetas = autoQueryList.stream().map(autoQuery -> {
            return new AutoQueryMeta(autoQuery.autoColumn(), autoQuery.foreignJoinColumn(), autoQuery.foreignKey(), autoQuery.foreignTable(), autoQuery.joinType(), autoQuery.extraColumns());
        }).collect(Collectors.toList());


        return autoQueryMetas;
    }


    /**
     * 重写拼接逻辑删除的方法, 主要适用与拼接多表的逻辑删除字段
     * @param newLine
     * @param table
     * @return
     */
    protected String mySqlWhereEntityWrapper(boolean newLine, TableInfo table, AutoQueryBuilder autoQueryBuilder) {
        if (table.isLogicDelete()) {
            String sqlScript = table.getAllSqlWhere(true, true, WRAPPER_ENTITY_DOT);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY),
                    true);

            //修改拼接logic语句的行为
            String logicDeleteSql = table.getLogicDeleteSql(true, false);
            List<AutoQueryMeta> autoQueryMetaList = autoQueryBuilder.getAutoQueryMetaList();
            logicDeleteSql = AutoQueryHelper.getLogicDeletedSql(autoQueryMetaList, logicDeleteSql);

            sqlScript += (NEWLINE + logicDeleteSql + NEWLINE);
            String normalSqlScript = SqlScriptUtils.convertIf(String.format("AND ${%s}", WRAPPER_SQLSEGMENT),
                    String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_NONEMPTYOFNORMAL), true);
            normalSqlScript += NEWLINE;
            normalSqlScript += SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT),
                    String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_EMPTYOFNORMAL), true);
            sqlScript += normalSqlScript;
            //这条语句观察了一下，我们Wrapper不可能为空
            sqlScript = SqlScriptUtils.convertChoose(String.format("%s != null", WRAPPER), sqlScript,
                    table.getLogicDeleteSql(false, false));
            sqlScript = SqlScriptUtils.convertWhere(sqlScript);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        }
        // 正常逻辑
        return super.sqlWhereEntityWrapper(newLine, table);
    }

}
