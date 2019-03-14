package com.lm.mybatisplus.autoquery.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lm.mybatisplus.autoquery.sqlhelper.AutoQueryHelper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author LM
 * @description: 用于发射queryWrapper对象
 * @date 2019/3/12 16:14
 */
@Slf4j
public class MyReflectionUtil {

    /**
     * 反射Wrapper对象, 反射修改wrapper字段, 添加where语句后表名的前缀
     * @TODO
     * @param queryWrapper
     */
    public static void reflectionWrapper(QueryWrapper queryWrapper) {

        String theAndConstant = " AND ";

        try {
            //变魔术,调用这个方法之后wrapper才会初始化SQl
            queryWrapper.getCustomSqlSegment();
            MergeSegments expression = queryWrapper.getExpression();
            //反射修改wrapper字段, 添加where语句后表名的前缀
            Class expressionClass = expression.getClass();
            Field expressionField = expressionClass.getDeclaredField("sqlSegment");
            expressionField.setAccessible(true);
            String expressionTemplate = (String)expressionField.get(expression);

            //分割列
            String[] partSqlList = expressionTemplate.split(theAndConstant);

            //对每个列名拼接前缀
            String completeWhereSql = Arrays.stream(partSqlList).map(partsql -> {

                if (StringUtils.isEmpty(partsql) || partsql.split("=")[0].contains(".")) {
                    return partsql;
                }

                return AutoQueryHelper.MAIN_TABLE_PRE + partsql;
            }).collect(Collectors.joining(theAndConstant));

            expressionField.set(expression, completeWhereSql);
        } catch(Exception e) {
            e.printStackTrace();
            log.error("反射修改wrapper对象失败, msg: {}", e.getMessage());
        }

    }





}
