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
public interface AutoQueryMapper<T,S> {

    List<S> autoQuery(@Param(Constants.WRAPPER) QueryWrapper<S> wrapper);

    List<S> autoQuery(IPage<T> page, @Param(Constants.WRAPPER) QueryWrapper<S> wrapper);

}
