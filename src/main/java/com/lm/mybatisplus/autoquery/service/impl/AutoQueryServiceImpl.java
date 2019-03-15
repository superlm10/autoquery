package com.lm.mybatisplus.autoquery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.mybatisplus.autoquery.mapper.AutoQueryMapper;
import com.lm.mybatisplus.autoquery.service.AutoQueryService;
import com.lm.mybatisplus.autoquery.utils.MyReflectionUtil;

import java.util.List;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 15:38
 */
public abstract class AutoQueryServiceImpl<M extends AutoQueryMapper<T, V>,T, V> extends ServiceImpl<M, T> implements AutoQueryService<T, V> {

    /**
     * 自动查询服务(不分页)
     * @param wrapper
     * @return
     */
    @Override
    public List<V> autoQuery(QueryWrapper<T> wrapper) {
        MyReflectionUtil.reflectionWrapper(wrapper);
        return baseMapper.autoQuery(wrapper);
    }


    /**
     * 自动查询服务(分页)
     * @param wrapper
     * @return
     */
    @Override
    public IPage<V> autoQuery(IPage<T> page, QueryWrapper<T> wrapper) {
        MyReflectionUtil.reflectionWrapper(wrapper);
        return baseMapper.autoQuery(page, wrapper);
    }

}
