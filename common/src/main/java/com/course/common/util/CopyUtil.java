package com.course.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/10  18:08
 * @description :这个是封装了拷贝实体类的代码，可以把控制层的交互实体类和Dao层的交互实体类实现拷贝转换
 * @modified By :
 */
public class CopyUtil {
    /**
      * create by    :李俨稹
      * description  ：循环重复第一个,拷贝多个实体(拷贝一个实体list列表)
      * create time  ：2020/8/10  18:26
      * @Param: source
 * @Param: clazz
      * @return java.util.List<T>
      */
    public static <T> List<T> copyList(List source,Class<T> clazz){
        List<T> target=new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source){
                T obj = copy(c,clazz);
                target.add(obj);
            }
        }
        return target;
    }
    /**
      * description  ：用来拷贝单个实体的方法
      * @Param: source 传入的数据,要拷贝的实体
      * @Param: clazz  传出的数据,拷贝完成的实体
      * @return T
      */
    public static <T> T copy(Object source,Class<T> clazz){
        if (source==null){
            return null;
        }
        T obj = null;
        try {
            obj=clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source,obj);
        return obj;
    }
}
