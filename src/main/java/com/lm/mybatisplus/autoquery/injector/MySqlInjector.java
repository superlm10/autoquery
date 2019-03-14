package com.lm.mybatisplus.autoquery.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.lm.mybatisplus.autoquery.methods.AutoQueryMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * @author LM
 * @description: 自定义SQL注入器
 * @date 2019/3/12 15:38
 */
@Component
public class MySqlInjector extends LogicSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        //增加自定义方法
        methodList.add(new AutoQueryMethod());
        return methodList;
    }

    /**
     * 提取泛型模型,多泛型的时候请将泛型T放在第一位
     *
     * @param mapperClass mapper 接口
     * @return mapper 泛型
     */
    @Override
    protected Class<?> extractModelClass(Class<?> mapperClass) {
        Type[] types = mapperClass.getGenericInterfaces();
        ParameterizedType target = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                Type[] typeArray = ((ParameterizedType) type).getActualTypeArguments();
                if (ArrayUtils.isNotEmpty(typeArray) && typeArray.length == 2) {
                    Type t = typeArray[1];
                    if (t instanceof TypeVariable || t instanceof WildcardType) {
                        break;
                    } else {
                        target = (ParameterizedType) type;
                        break;
                    }
                }
                break;
            }
        }
        return target == null ? null : (Class<?>) target.getActualTypeArguments()[1];
    }
}
