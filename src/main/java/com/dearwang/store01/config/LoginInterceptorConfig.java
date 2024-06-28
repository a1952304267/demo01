package com.dearwang.store01.config;


import com.dearwang.store01.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
//注册拦截器时仅需使用该接口中的addInterceptors将自定义的拦截器进行注册

//完成处理器拦截器的注册
@Configuration//加载并进行注册
public class LoginInterceptorConfig implements WebMvcConfigurer {



//    配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //    创建拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //    配置白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/web/components/**");

//        完成注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);/*添加拦截路径参数url为什么*/
    }
}
