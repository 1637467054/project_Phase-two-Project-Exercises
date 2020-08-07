package com.course.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version :
 * @auto :李俨稹
 * @date ：Created in 2020/8/7  16:42
 * @description :这个是用来解决vue跨域问题的配置代码
 * @modified By :V 1.0
 */
@Configuration
public class ConsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600);
                //1小时内不需要再预检(发OPTIONS请求)
    }
}
