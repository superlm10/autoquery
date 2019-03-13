package com.lm.mybatisplus.autoquery.demo.mapper;

import com.lm.mybatisplus.autoquery.demo.entity.Student;
import com.lm.mybatisplus.autoquery.mapper.AutoQueryMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 15:50
 */
@Mapper
public interface StudentMapper extends AutoQueryMapper<Student> {



}
