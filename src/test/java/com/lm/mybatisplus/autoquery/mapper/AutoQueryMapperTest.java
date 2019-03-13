package com.lm.mybatisplus.autoquery.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lm.mybatisplus.autoquery.MybatisplusAutoqueryApplicationTests;
import com.lm.mybatisplus.autoquery.annotations.AutoQuery;
import com.lm.mybatisplus.autoquery.demo.entity.Student;
import com.lm.mybatisplus.autoquery.demo.vo.StudentVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author LM
 * @description:
 * @date 2019/3/13 14:49
 */
public class AutoQueryMapperTest extends MybatisplusAutoqueryApplicationTests {

    @Autowired
    private AutoQueryMapper<StudentVo, StudentVo> autoQueryMapper;

    @Test
    public void test() {
        QueryWrapper<StudentVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_age", 20);
        queryWrapper.eq("class_id", 1);

        List<StudentVo> list = autoQueryMapper.autoQuery(queryWrapper);
        System.out.println(list);

    }


}