package com.course.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableEurekaClient//这是用来向注册中心注册的注解
public class GatewayApplication {
    /**
     * SpringBoot默认集成slf4j框架,它定义了一些日志接口,规范日志输出,真正的是
     * 现实     logback     或log4j.我们代码中写的都是slf4j的代码.
     */
    private static final Logger LOG= LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(GatewayApplication.class);
        Environment env=app.run(args).getEnvironment();
        LOG.info("启动成功功！！");
        //下面这行代码是老师的代码
//        LOG.info("Eureka地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));

        LOG.info("Gateway地址：\t"+new ServerConfig().getUrl()+env.getProperty("server.port"));

    }

    /**
      * create by    :李俨稹
      * description  ：这个是用来获取当前工程ip地址的内部类
      * create time  ：2020/7/31  19:30
      * @Param: null
      * @return 
      */
    public static class ServerConfig  implements ApplicationListener<WebServerInitializedEvent> {

        public String getUrl() {
            InetAddress address = null;
            try {
                address = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return "http://"+address.getHostAddress() +":";
        }

        @Override
        public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {

        }
    }
    /**
      * create by    :李俨稹
      * description  ：这个是用来解决gateway跨域问题的代码，如果有多个用于写controller层的分布式可以
      *                用来这个gateway来解决跨域问题，但是如果只有一个或少个可以直接把这个配置代码写到
      *                公共类或者controller的类中，不影响使用(如果只有一个在这里的配置的代码位置是
      *                com/course/common/config/ConsConfig.java中)
     *
      * create time  ：2020/8/8  8:50
      * @Param: 
      * @return org.springframework.web.cors.reactive.CorsWebFilter
      */
    @Bean
    public CorsWebFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(Boolean.TRUE);
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**",config);
        return new CorsWebFilter(source);
    }

}
