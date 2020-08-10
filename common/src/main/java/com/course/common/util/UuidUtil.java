package com.course.common.util;

import java.util.UUID;

public class UuidUtil {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    /**
     * 获取短UUID
     * @return
     */
    public static String getShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        //调用写好的方法,获取去掉"-"号的32位16进制的uuid
        String uuid = UuidUtil.getUuid();
        for (int i = 0; i < 8; i++) {
            //public String substring(int beginIndex, int endIndex)使用Spring的方法对字符串进行截取,每次截取四个
            String str = uuid.substring(i * 4, i * 4 + 4);
//            把这字符串以16进制的方式转换成为10进制的int类型的数字
            int x = Integer.parseInt(str, 16);
            // 对62取余   (26个英文字母*2+10[0~9])    也就是上面的chars数组
            shortBuffer.append(chars[x % 0x3E]);
//            shortBuffer.append(chars[x % 62]);E是16进制的62,这个代码和上面的代码得到的结果相同
        }
        return shortBuffer.toString();

    }

    /**
     * 获得32位UUID
     */
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getShortUuid());
    }
}




















