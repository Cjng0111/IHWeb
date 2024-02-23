package com.cjng.management.config;

import com.cjng.management.interceptor.LoginCheckInterceptor;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @Description:
 * Spring MVC的配置类，用于配置拦截器和服务器。
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // 登录检查拦截器
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");

    }

    /**
     * @Description:
     * 配置SAXReader Bean，用于解析XML文件
     * 是试验bean的相关配置，与项目代码无关
     * bean默认是方法名
     * */
    @Bean
    public SAXReader saxReader() {
        return new SAXReader();
    }
}
