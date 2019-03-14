package com.lm.mybatisplus.autoquery.demo.service.impl;

import com.lm.mybatisplus.autoquery.demo.entity.Student;
import com.lm.mybatisplus.autoquery.demo.mapper.StudentMapper;
import com.lm.mybatisplus.autoquery.demo.service.StudentService;
import com.lm.mybatisplus.autoquery.demo.vo.StudentVo;
import com.lm.mybatisplus.autoquery.service.impl.AutoQueryServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 15:51
 */
@Service
public class StudentServiceImpl extends AutoQueryServiceImpl<StudentMapper, Student, StudentVo> implements StudentService {
}
