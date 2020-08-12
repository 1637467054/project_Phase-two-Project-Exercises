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
    static String toServerPath="common\\src\\main\\java\\com\\course\\common\\service\\";
    public static void main(String[] args) throws IOException, TemplateException {
        String Domain="Section";
        String domain="section";
        Map<String,Object> map=new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);

        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServerPath+Domain+"Service.java",map);
    }
}
