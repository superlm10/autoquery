package com.lm.mybatisplus.autoquery.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.mybatisplus.autoquery.mapper.AutoQueryMapper;
import com.lm.mybatisplus.autoquery.utils.MyReflectionUtil;
import com.lm.mybatisplus.autoquery.utils.SpringContextUtils;

import java.util.List;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 19:29
 */
public interface AutoQueryService<T> {

    /**
     * 自动查询服务(不分页)
     * @param wrapper
     * @return
     */
    default List<T> autoQuery(QueryWrapper<T> wrapper) {
        AutoQueryMapper autoQueryMapper = (AutoQueryMapper)SpringContextUtils.getBean("autoQueryMapper");
        MyReflectionUtil.reflectionWrapper(wrapper);
        return autoQueryMapper.autoQuery(wrapper);
    }


    /**
     * 自动查询服务(分页)
     * @param wrapper
     * @return
     */
    default List<T> autoQuery(IPage<T> page, QueryWrapper<T> wrapper) {
        AutoQueryMapper autoQueryMapper = (AutoQueryMapper)SpringContextUtils.getBean("autoQueryMapper");
        MyReflectionUtil.reflectionWrapper(wrapper);
        return autoQueryMapper.autoQuery(page, wrapper);
    }


}
