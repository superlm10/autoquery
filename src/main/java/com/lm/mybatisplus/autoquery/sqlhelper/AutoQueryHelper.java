package com.lm.mybatisplus.autoquery.sqlhelper;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author LM
 * @description: 自动查询工具类,提供sql语句的构造与拼接
 * @date 2019/3/12 11:31
 */
@Slf4j
public class AutoQueryHelper {

    /**
     * 搜索外键表的前缀
     */
    private static final String FORIGN_TABLE_PRE = "rel%s.";

    /**
     * 外键表后缀
     */
    private static final String FORIGN_TABLE_POST = " rel%s";

    /**
     * 主表前缀
     */
    public static final String MAIN_TABLE_PRE = "main.";

    /**
     * 主表后缀
     */
    private static final String MAIN_TABLE_POST = " main";

    /**
     * 构造select查询列
     * @param selectColumn
     * @param relColumn
     * @param extraColumns 额外查询列
     * @param joinIndex 如果是多个表连接, 需要通过该字段判断, 赋予列名不同的别名
     * @return
     */
    public static String buildSelectColumn(String selectColumn, String relColumn, String[] extraColumns, int joinIndex) {
        String preSelectColumn = getPreSelectColumn(selectColumn);
        String buildSelectColumn = addRelColumn(relColumn, preSelectColumn, joinIndex);

        for (int i = 0; i < extraColumns.length; i++) {
            //添加额外查询字段
            buildSelectColumn = AutoQueryHelper.addRelColumn(extraColumns[i], buildSelectColumn, joinIndex);
        }

        return buildSelectColumn;
    }

    /**
     * 构造from后面的连表sql语句
     * @param autoQueryMetaList
     * @param mainTableName
     * @return
     */
    public static String buildTableByMeta(List<AutoQueryMeta> autoQueryMetaList, String mainTableName) {

        StringBuilder joinBuilder = new StringBuilder();
        //加后缀的完整表名
        String wholeMainTable = mainTableName + MAIN_TABLE_POST;
        joinBuilder.append(wholeMainTable);

        //格式为 连表类型 + 表A + ON + 连接字段匹配
        String joinTemplate = " %s %s ON %s = %s";
        int size = autoQueryMetaList.size();
        for (int metaIndex = 0; metaIndex < size; metaIndex++) {

            AutoQueryMeta autoQueryMeta = autoQueryMetaList.get(metaIndex);

            String foreignKey = autoQueryMeta.getForeignKey();
            String foreignTable = autoQueryMeta.getForeignTable();
            String relationColumn = autoQueryMeta.getRelationColumn();
            String joinType = autoQueryMeta.getJoinType();

            String wholeForeignTable = foreignTable + String.format(FORIGN_TABLE_POST, metaIndex);

            //加前缀的完整关联字段
            String mainJoinKey = MAIN_TABLE_PRE + foreignKey;
            //适配非ID连接, 前缀加metaIndex(第几个关联表)
            String foreignJoinKey = String.format(FORIGN_TABLE_PRE, metaIndex) + relationColumn;

            String partJoinSql = String.format(joinTemplate, joinType, wholeForeignTable, mainJoinKey, foreignJoinKey);
            joinBuilder.append(partJoinSql);
        }

        return joinBuilder.toString();

    }

    /**
     * 搜索条件加入外键查询字段
     * @param relColumn
     * @param selectColumn (需要已完成前缀添加)
     * @return
     */
    public static String addRelColumn(String relColumn, String selectColumn, int joinIndex) {

        StringBuilder builder = new StringBuilder();
        builder.append(selectColumn);
        builder.append(",");
        builder.append(String.format(FORIGN_TABLE_PRE, joinIndex) + relColumn);

        return builder.toString();
    }

    /**
     * 得到前缀+列名的拼接
     * @param selectColumn
     * @return
     */
    public static String getPreSelectColumn(String selectColumn) {

        StringBuilder selectColumnWithPre = new StringBuilder();

        String[] column = selectColumn.split(",");

        for (int i = 0; i < column.length; i++) {
            String oriColumn = column[i];
            selectColumnWithPre.append(MAIN_TABLE_PRE + oriColumn);
            if (i != column.length - 1) {
                selectColumnWithPre.append(",");
            }
        }

        return selectColumnWithPre.toString();
    }

    /**
     * 原始的列名中添加额外查询的列
     * @param oriSelectColumn (不带有前缀的列名)
     * @return
     */
    public static String addExtraColumns(String oriSelectColumn, String[] extraColumns) {

        if (extraColumns == null && extraColumns.length == 0) {
            return oriSelectColumn;
        }

        StringBuilder extraBuilder = new StringBuilder();
        extraBuilder.append(oriSelectColumn);

        for (int i = 0; i < extraColumns.length; i++) {
            extraBuilder.append(",");
            extraBuilder.append(extraColumns[0]);
        }

        return extraBuilder.toString();
    }

}
