package com.course.business.controller;

import com.course.common.dto.ResponseDto;
import com.course.common.exception.ValidatorException;
import com.course.common.util.ValidatorUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/11  19:57
 * @description :Conteroller层的统一异常处理
 * @modified By :
 */

/**
 * @ControllerAdvice ，很多初学者可能都没有听说过这个注解，实际上，这是一个非常有用的注解，顾名思义，这是一个增
 * 强的 Controller。使用这个 Controller ，可以实现三个方面的功能：
 *     一、全局异常处理
 *     二、全局数据绑定
 *     三、全局数据预处理
 * 灵活使用这三个功能，可以帮助我们简化很多工作，需要注意的是，这是 SpringMVC 提供的功能，在 Spring Boot 中可以
 * 直接使用。
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
      * description  ：只要触发这个异常就会被这个方法所拦截，就会直接返回这了处理好的内容了
      * @Param: e
      * @return com.course.common.dto.ResponseDto
      */
//    @ExceptionHandler(value = ValidatorException.class)
    @ExceptionHandler(ValidatorException.class)
    //@ExceptionHandler 注解用来指明异常的处理类型，即如果这里指定为 NullpointerException，
    // 则数组越界异常就不会进到这个方法中来。
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e){
        ResponseDto responseDto=new ResponseDto();
        responseDto.setSuccess(false);
        responseDto.setMessage("请求参数异常！");
        return responseDto;
    }
}
