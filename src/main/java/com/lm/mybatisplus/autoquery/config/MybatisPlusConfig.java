package com.lm.mybatisplus.autoquery.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description TODO
 * @Author LM
 * @Date 2019/3/12 0012 下午 8:23
 * @Version 1.0
 **/
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor getPaginationInterceptor() {
        return new PaginationInterceptor();
    }


}
