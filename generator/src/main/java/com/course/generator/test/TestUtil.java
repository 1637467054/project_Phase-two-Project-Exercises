package com.course.generator.test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestUtil {

    /**
     * 模板文件所在路径
     */
    static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";
    /**
     * 要生成文件的路径
     */
    static String toPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";

    public static void main(String[] args) throws IOException, TemplateException {
        //后面的VERSION_2_3_29是freemarker的版本号(2.3.29)
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        //读模板所在的路径
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        //后面的VERSION_2_3_29是freemarker的版本号(2.3.29)
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        //读具体路径下的某一个模板
        Template temp = cfg.getTemplate("test.ftl");

        //根据模板要生成一个什么样的文件
        FileWriter fw = new FileWriter(toPath + "Test.java");
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(null, bw);
        bw.flush();
        fw.close();
    }
}
