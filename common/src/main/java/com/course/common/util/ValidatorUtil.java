package com.course.common.util;

import com.course.common.exception.ValidatorException;
import org.springframework.util.StringUtils;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/11  18:12
 * @description :这是后端校验的工具类，防止有人恶意直接发送错误请求
 * @modified By :
 */
public class ValidatorUtil {
    /**
     * 空校验
     * @param str
     * @param fieldName
     */
    public static void require(String str,String fieldName){
        if (StringUtils.isEmpty(str)){
            throw new ValidatorException(fieldName+"不能为空");
        }
    }
    /**
      * description  ：这个是长度校验
      * @Param: str         被校验的字符串
      * @Param: fieldName   被校验的字段名
      * @Param: min         最小长度
      * @Param: max         最大长度
      */
    public static void length(String str,String fieldName,int min,int max){
        //如果传进来的值为空我们就直接return,用StringUtil判断同时判断了为null时返回false,用String自带的isEmpty判断为null时会报空指针
        if (StringUtils.isEmpty(str)){
            return;
        }
        int length=0;
        if (!StringUtils.isEmpty(str)){
            length=str.length();
        }
        if (length<min||length>max){
            throw new ValidatorException(fieldName+"长度必须为"+min+"~"+max+"位");
        }
    }
}
