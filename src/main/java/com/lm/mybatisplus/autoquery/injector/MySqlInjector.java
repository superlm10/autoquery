package com.lm.mybatisplus.autoquery.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.lm.mybatisplus.autoquery.methods.AutoQueryMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LM
 * @description: 自定义SQL注入器
 * @date 2019/3/12 15:38
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        //增加自定义方法
        methodList.add(new AutoQueryMethod());
        return methodList;
    }
}
