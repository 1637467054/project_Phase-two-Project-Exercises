package com.course.common.exception;

/**
 * @description :自定义异常类
 * 自定义异常可以集成RuntimeException或Exception,一般项目内部的业务异常,可以用
 * RuntimeException,不需要try catch.如果是开发一些框架或工具类,明确告诉外部需要
 * 做异常处理的,可以用Exception.另外还需要考虑事务中的异常处理
 */
public class ValidatorException extends RuntimeException {
    public ValidatorException(String message){
        super(message);
    }
}
