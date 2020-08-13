package com.course.generator.main;

import com.course.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/12  21:04
 * @description :代码生成器
 * @modified By :
 */
public class MainGenerator {
    /**
     * MODULE是模块名称
     */
    static String MODULE = "business";
    static String toServerPath="common\\src\\main\\java\\com\\course\\common\\service\\";
    static String toControllerPath=MODULE+"\\src\\main\\java\\com\\course\\"+MODULE+"\\controller\\admin\\";

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain="Section";
        String domain="section";
        String tableNameCn="小节";
        String module=MODULE;
        Map<String,Object> map=new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("tableNameCn",tableNameCn);
        map.put("module",module);

        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServerPath+Domain+"Service.java",map);

        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);

    }
}
