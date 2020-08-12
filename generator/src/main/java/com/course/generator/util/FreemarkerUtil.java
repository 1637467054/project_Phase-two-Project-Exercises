package com.course.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/12  20:56
 * @description :代码生成器工具包
 * @modified By :V1.0
 */
public class FreemarkerUtil {

    static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\ftl\\";
    static Template temp;

    public static void initConfig(String ftlName) throws IOException {
        //后面的VERSION_2_3_29是freemarker的版本号(2.3.29)
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        //读模板所在的路径
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        //后面的VERSION_2_3_29是freemarker的版本号(2.3.29)
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        //读具体路径下的某一个模板
        temp = cfg.getTemplate(ftlName);
    }

    public static void generator(String fileName, Map<String,Object> map) throws IOException, TemplateException {
        //根据模板要生成一个什么样的文件
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(map, bw);
        bw.flush();
        fw.close();
    }
}
