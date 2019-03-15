package com.lm.mybatisplus.autoquery.methods;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import static org.junit.Assert.*;

/**
 * @author LM
 * @description:
 * @date 2019/3/15 9:23
 */
public class AutoQueryMethodTest {

    @Test
    public void replaceTableName() {
        String tableName = "Student";
        System.out.println(tableName.concat("Vo"));
        if (tableName.contains("Entity")) {
            String theReplaceTable = tableName.replace("Entity", "Vo");
            System.out.println(theReplaceTable);
        }

    }

}