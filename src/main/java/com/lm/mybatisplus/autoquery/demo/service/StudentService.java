package com.lm.mybatisplus.autoquery.demo.service;

import com.lm.mybatisplus.autoquery.demo.entity.Student;
import com.lm.mybatisplus.autoquery.demo.vo.StudentVo;
import com.lm.mybatisplus.autoquery.service.AutoQueryService;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 15:49
 */
public interface StudentService extends AutoQueryService<Student, StudentVo> {



}
