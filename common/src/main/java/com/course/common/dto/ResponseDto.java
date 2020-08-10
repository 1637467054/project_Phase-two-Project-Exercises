package com.course.common.dto;

import lombok.Data;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/10  18:34
 * @description :这是用来定义返回数据统一的代码
 * @modified By :
 */
@Data
public class ResponseDto<T> {

    /**
     * 业务上的成功或失败
     */
    private boolean success=true;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据,自定义类型
     */
    private T content;
}
