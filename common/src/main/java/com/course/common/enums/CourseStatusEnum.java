package com.course.common.enums;

public enum CourseStatusEnum {
    /**
     * 这个是草稿是否发布的枚举
     */
    PUBLISH("p","发布"),

    DRAFT("D","草稿");


    private String code;

    private String desc;

    CourseStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
