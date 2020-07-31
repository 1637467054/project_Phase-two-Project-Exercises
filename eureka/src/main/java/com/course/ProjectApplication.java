package com.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import javax.swing.*;

@SpringBootApplication
@EnableEurekaServer//在项目启动类上使用@EnableEurekaServer，可以将项目作为SpringCloud中的注册中心。
public class ProjectApplication {
    /**
     * SpringBoot默认集成slf4j框架,它定义了一些日志接口,规范日志输出,真正的是
     * 现实     logback     或log4j.我们代码中写的都是slf4j的代码.
     */
    private static final Logger LOG= LoggerFactory.getLogger(ProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(ProjectApplication.class);
        Environment env=app.run(args).getEnvironment();
        LOG.info("启动成功功！！");
        LOG.info("Eureka地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));
    }

}
