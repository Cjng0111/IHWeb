package com.cjng.management.config;

import com.cjng.management.interceptor.LoginCheckInterceptor;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // 登录检查拦截器
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");

    }

    // 配置SAXReader Bean
    // 用于解析XML文件
    // bean默认是方法名
    @Bean
    public SAXReader saxReader() {
        return new SAXReader();
    }
}
