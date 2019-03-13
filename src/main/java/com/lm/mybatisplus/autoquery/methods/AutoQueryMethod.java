package com.lm.mybatisplus.autoquery.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.lm.mybatisplus.autoquery.annotations.AutoQuery;
import com.lm.mybatisplus.autoquery.sqlhelper.AutoQueryBuilder;
import com.lm.mybatisplus.autoquery.sqlhelper.AutoQueryHelper;
import com.lm.mybatisplus.autoquery.sqlhelper.AutoQueryMeta;
import com.lm.mybatisplus.autoquery.sqlhelper.MySqlMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.lang.reflect.Field;
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

        String completeSql = getCompleteSql(completeSqlNotWhere, tableInfo);
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
    private String getCompleteSql(String completeSqlNotWhere, TableInfo tableInfo) {
        return String.format(completeSqlNotWhere, sqlWhereEntityWrapper(true, tableInfo));
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


}
