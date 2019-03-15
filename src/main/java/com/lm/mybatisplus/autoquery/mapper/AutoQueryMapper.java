package com.lm.mybatisplus.autoquery.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生Mapper层
 * @author nieqiurong 2018/8/11 20:21.
 */
@Mapper
public interface AutoQueryMapper<T, V> extends BaseMapper<T> {

    List<V> autoQuery(@Param(Constants.WRAPPER) QueryWrapper<T> wrapper);

    IPage<V> autoQuery(IPage<T> page, @Param(Constants.WRAPPER) QueryWrapper<T> wrapper);

}
