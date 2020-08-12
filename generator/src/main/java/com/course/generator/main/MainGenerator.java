package com.course.generator.main;

import com.course.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/12  21:04
 * @description :代码生成器
 * @modified By :
 */
public class MainGenerator {
    static String toPath="generator\\src\\main\\java\\com\\course\\generator\\test\\";
    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        FreemarkerUtil.generator(toPath+"Test.java");
    }
}
