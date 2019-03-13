package com.lm.mybatisplus.autoquery.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 19:29
 */
public interface AutoQueryService<T> extends IService<T> {

    /**
     * 自动查询服务(不分页)
     * @param wrapper
     * @return
     */
    List<T> autoQuery(QueryWrapper<T> wrapper);

    /**
     * 自动查询服务(分页)
     * @param wrapper
     * @return
     */
    List<T> autoQuery(IPage<T> page, QueryWrapper<T> wrapper);


}
