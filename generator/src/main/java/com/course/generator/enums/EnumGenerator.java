package com.course.generator.enums;

import com.course.common.enums.*;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnumGenerator {
    static String path = "admin\\public\\static\\js\\enums.js";
//    static String path = "web\\public\\static\\js\\enums.js";

    public static void main(String[] args) {
        StringBuffer bufferObject = new StringBuffer();
        StringBuffer bufferArray = new StringBuffer();
        //获取现在(开始)时间
        long begin = System.currentTimeMillis();
        try {
            toJson(SectionChargeEnum.class, bufferObject, bufferArray);
            toJson(YesNoEnum.class, bufferObject, bufferArray);
//            toJson(CourseLevelEnum.class, bufferObject, bufferArray);
//            toJson(CourseChargeEnum.class, bufferObject, bufferArray);
//            toJson(CourseStatusEnum.class, bufferObject, bufferArray);
//            toJson(FileUseEnum.class, bufferObject, bufferArray);
//            toJson(SmsUseEnum.class, bufferObject, bufferArray);
//            toJson(SmsStatusEnum.class, bufferObject, bufferArray);

            StringBuffer buffer = bufferObject.append("\r\n").append(bufferArray);
            writeJs(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行耗时:" + (end - begin) + " 毫秒");
    }

    private static void toJson(Class clazz, StringBuffer bufferObject, StringBuffer bufferArray) throws Exception {
        //getSimpleName()获取类的名称(toUnderline是把SectionChargeEnum=>SECTION_CHARGE,YesNoEnum=>YES_NO)
        String key = toUnderline(clazz.getSimpleName());
        toJson(clazz, key, bufferObject, bufferArray);
    }

    private static void toJson(Class clazz, String key, StringBuffer bufferObject, StringBuffer bufferArray) throws Exception {
        Object[] objects = clazz.getEnumConstants();
        Method name = clazz.getMethod("name");
        Method getDesc = clazz.getMethod("getDesc");
        Method getCode = clazz.getMethod("getCode");

        // 生成对象
        bufferObject.append(key).append("={");
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            if (getCode == null) {
                bufferObject.append(name.invoke(obj)).append(":{key:\"").append(name.invoke(obj)).append("\", value:\"").append(getDesc.invoke(obj)).append("\"}");
            } else {
                bufferObject.append(name.invoke(obj)).append(":{key:\"").append(getCode.invoke(obj)).append("\", value:\"").append(getDesc.invoke(obj)).append("\"}");
            }
            if (i < objects.length - 1) {
                bufferObject.append(",");
            }
        }
        bufferObject.append("};\r\n");

        // 生成数组
        bufferArray.append(key).append("_ARRAY=[");
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            if (getCode == null) {
                bufferArray.append("{key:\"").append(name.invoke(obj)).append("\", value:\"").append(getDesc.invoke(obj)).append("\"}");
            } else {
                bufferArray.append("{key:\"").append(getCode.invoke(obj)).append("\", value:\"").append(getDesc.invoke(obj)).append("\"}");
            }
            if (i < objects.length - 1) {
                bufferArray.append(",");
            }
        }
        bufferArray.append("];\r\n");
    }

    /**
     * 写文件
     * @param stringBuffer
     */
    public static void writeJs(StringBuffer stringBuffer) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            System.out.println(path);
            osw.write(stringBuffer.toString());
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 功能：驼峰转大写下划线，并去掉_ENUM
     * 如：SectionChargeEnum 变成 SECTION_CHARGE
     * @param str
     * @return
     */
    public static String toUnderline(String str) {
        String result = underline(str).toString();
        return result.substring(1, result.length()).toUpperCase().replace("_ENUM", "");
    }

    /**
     * 驼峰转下划线，第一位是下划线
     * 如：SectionChargeEnum 变成 _section_charge_enum
     * @param str
     * @return
     */
    private static StringBuffer underline(String str) {
        //正则表达式，匹配所有大写字母(就是用A-Z作为模板)
        Pattern pattern = Pattern.compile("[A-Z]");
        //创建一个匹配此模式的给定输入的匹配器
        //
        // Matcher：
        //
        //通过解释 Pattern 对 character sequence 执行匹配操作的引擎。
        //
        //通过调用模式的 matcher 方法从模式创建匹配器。创建匹配器后，可以使用它执行三种不同的匹配操作：
        //
        //    matches 方法尝试将整个输入序列与该模式匹配。
        //
        //    lookingAt 尝试将输入序列从头开始与该模式匹配。
        //
        //    find 方法扫描输入序列以查找与该模式匹配的下一个子序列。
        //
        //每个方法都返回一个表示成功或失败的布尔值。通过查询匹配器的状态可以获取关于成功匹配的更多信息。
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        //如果有大写字符则处理，没有则返回
        if(matcher.find()) {
            sb = new StringBuffer();
            matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
            matcher.appendTail(sb);
        }else {
            return sb;
        }
        //如果有大写字符处理第一个字符后再递归调用本方法
        return underline(sb.toString());
    }
}
