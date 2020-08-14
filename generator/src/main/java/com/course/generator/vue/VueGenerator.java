package com.course.generator.vue;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
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
public class VueGenerator {
    /**
     * MODULE是模块名称
     */
    static String MODULE = "business";
    static String toVuePath = "admin\\src\\views\\admin\\";
    static String generatorConfigPath = "common\\src\\main\\resources\\generator\\generatorConfig.xml";

    public static void main(String[] args) throws IOException, TemplateException, SQLException, DocumentException {
        String module=MODULE;
        //只生成配置文件中的第一个table节点
        File file=new File(generatorConfigPath);
        SAXReader reader=new SAXReader();
        //读取xml文件到Document中
        Document doc=reader.read(file);
        //读取xml文件的根节点
        Element rootElement=doc.getRootElement();
        //读取context节点
        Element contextElement=rootElement.element("context");
        //定义一个Element用于遍历
        Element tableElement;
        //读取第一个"table"的节点
        tableElement=contextElement.elementIterator("table").next();

        String Domain=tableElement.attributeValue("domainObjectName");
        String tableName=tableElement.attributeValue("tableName");
        String tableNameCn=DbUtil.getTableComment(tableName);
        //substring(0,1)截取第一位字符串.toLowerCase()把字符串转换为小写
        String domain=Domain.substring(0,1).toLowerCase()+Domain.substring(1);

        System.out.println("表："+tableElement.attributeValue("tableName"));
        System.out.println("Domain:"+tableElement.attributeValue("domainObjectName"));

        //获取所有列信息
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
        FreemarkerUtil.initConfig("vue.ftl");
        FreemarkerUtil.generator(toVuePath +domain+".vue",map);

    }
    private static Set<String> getJavaTypes(List<Field> fieldList){
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field=fieldList.get(i);
            //获取java类型，这个是用来引入java包的
            set.add(field.getJavaType());
        }
        return set;
    }
}
