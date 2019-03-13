package com.lm.mybatisplus.autoquery.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lm.mybatisplus.autoquery.demo.entity.Student;
import com.lm.mybatisplus.autoquery.demo.vo.StudentVo;
import com.lm.mybatisplus.autoquery.mapper.AutoQueryMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 15:50
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student>, AutoQueryMapper<Student> {


}
