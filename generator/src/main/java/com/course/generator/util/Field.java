package com.course.generator.util;

import lombok.Data;

/**
 * @description :Field类，用于存储每个字段的信息。流程：根据表名获取所有的字段信息，再讲过字段信息填充到Field类中，得到Field列表，之后将Field列表变量传入模板
 */
@Data
public class Field {
    private String name; // 字段名：course_id
    private String nameHump; // 字段名小驼峰：courseId
    private String nameBigHump; // 字段名大驼峰：CourseId
    private String nameCn; // 中文名：课程
    private String type; // 字段类型：char(8)
    private String javaType; // java类型：String
    private String comment; // 注释：课程|ID
    private Boolean nullAble; // 是否可为空
    private Integer length; // 字符串长度
    private Boolean enums; // 是否是枚举
    private String enumsConst; // 枚举常量 COURSE_LEVEL
}
