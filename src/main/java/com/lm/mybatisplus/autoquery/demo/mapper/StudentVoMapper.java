package com.lm.mybatisplus.autoquery.demo.mapper;

import com.lm.mybatisplus.autoquery.demo.vo.StudentVo;
import com.lm.mybatisplus.autoquery.mapper.AutoQueryMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper继承AutoQueryMapper
 * @author LM
 * @description: 创建Vo类mapper, 如果不想在Entity写与表结构无关的字段, 实际场景：vo类返回数据给前端页面
 * @date 2019/3/13 11:53
 */
@Mapper
public interface StudentVoMapper extends AutoQueryMapper<StudentVo> {
}
