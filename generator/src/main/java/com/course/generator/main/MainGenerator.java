package com.course.generator.main;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

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
    static String toDtoPath="common\\src\\main\\java\\com\\course\\common\\dto\\";
    static String toServerPath="common\\src\\main\\java\\com\\course\\common\\service\\";
    static String toControllerPath=MODULE+"\\src\\main\\java\\com\\course\\"+MODULE+"\\controller\\admin\\";

    public static void main(String[] args) throws IOException, TemplateException, SQLException {
        String Domain="Section";
        String domain="section";
        String tableNameCn="小节";
        String module=MODULE;
        List<Field> fieldList= DbUtil.getColumnByTableName(domain);
        Set<String> typeSet=getJavaTypes(fieldList);
        Map<String,Object> map=new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("tableNameCn",tableNameCn);
        map.put("module",module);
        map.put("fieldList",fieldList);
        map.put("typeSet",typeSet);
        //生成dto
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(toDtoPath+Domain+"Dto.java",map);

        //生成service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServerPath+Domain+"Service.java",map);

        //生成controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);

    }
    private static Set<String> getJavaTypes(List<Field> fieldList){
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field=fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
