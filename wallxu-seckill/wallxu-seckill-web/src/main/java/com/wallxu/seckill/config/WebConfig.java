package com.wallxu.seckill.config;

import com.wallxu.seckill.interceptor.AccessInterceptor;
import com.wallxu.seckill.interceptor.UserHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by Administrator on 2018/7/31.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {

        //请求拦截器，过滤非法请求
        registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/**");
    }

    //参数解析器
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserHandlerMethodArgumentResolver());
    }
}
