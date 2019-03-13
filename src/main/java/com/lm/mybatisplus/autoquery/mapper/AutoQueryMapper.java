package com.lm.mybatisplus.autoquery.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 15:50
 */
@Mapper
public interface AutoQueryMapper<T> {

    List<T> autoQuery(@Param(Constants.WRAPPER) QueryWrapper<T> wrapper);

    List<T> autoQuery(IPage<T> page, @Param(Constants.WRAPPER) QueryWrapper<T> wrapper);

}
