package com.course.generator.util;

import com.course.generator.enums.EnumGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/13  17:13
 * @description :这是用来连接数据库的工具类
 * @modified By :
 */
public class DbUtil {
    private static final Pattern linePattern=Pattern.compile("_(\\w)");
    /**
      * description  ：获取数据库的连接
      * @return java.sql.Connection
      */
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/zuoye007";
            String user="root";
            String password="1637467054";
            conn= DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
      * description  ：获取表的注释(其实就是获得表的中文名称)
      * @Param: tableName
      * @return java.lang.String
      */
    public static String getTableComment(String tableName) throws SQLException {
        Connection conn=getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select TABLE_COMMENT from information_schema.TABLES where TABLE_NAME='"+tableName+"'");
        String tableNameCH="";
        if (rs!=null){
            while (rs.next()){
                tableNameCH=rs.getString("table_comment");
                break;
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("表名："+tableNameCH);
        return tableNameCH;
    }
    /**
      * description  ：获取所有列信息
      * @Param: tableName
      * @return java.util.List<java.lang.reflect.Field>
      */
    public static List<Field> getColumnByTableName(String tableName) throws SQLException {
        List<Field> fieldList=new ArrayList<>();
        Connection conn=getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("show full columns from `"+tableName+"`");
        if (rs!=null){
            while(rs.next()){
                // 获取字段名：course_id
                String columnName=rs.getString("Field");
                // 获取字段类型：char(8)
                String type=rs.getString("Type");
                // 获取注释内容(中文名)
                String comment=rs.getString("Comment");
                //YES NO(查看当前列是否可以为空，能则返回YES 不能则返回NO)
                String nullAble=rs.getString("Null");
                Field field=new Field();
                field.setName(columnName);
                field.setNameHump(lineToHump(columnName));
                field.setNameBigHump(lineToBigHump(columnName));
                field.setType(type);
                field.setJavaType(DbUtil.sqlTypeToJavaType(rs.getString("Type")));
                field.setComment(comment);
                //contains当且仅当此字符串包含指定的char值序列时，返回true。
                if (comment.contains("|")){
                    field.setNameCn(comment.substring(0,comment.indexOf("|")));
                }else {
                    field.setNameCn(comment);
                }
                //允许为空返回ture,不能为空返回false
                field.setNullAble("YES".equals(nullAble));
                //String.toUpperCase是将小写转为大写,contains当且仅当此字符串包含指定的char值序列时，返回true。
                if (type.toUpperCase().contains("varchar".toUpperCase())){
                    //indexOf是返回某个字符串在这个字符串中出现的第一此位置的索引，如果没有出现则返回-1,substring是截取字符串(这个判断大概就是如果这个字段在数据库中是varchar类型的话就获取varchar(数字)中的数字)
                    String lengthStr=type.substring(type.indexOf("(")+1,type.length()-1);
                    //把数字转换为Integer包装类传入实体
                    field.setLength(Integer.valueOf(lengthStr));
                }else {
                    //如果不是"varchar"类型就返回0
                    field.setLength(0);
                }
                //判断是否是枚举类型
                if (comment.contains("枚举")) {
                    //如果是枚举类型则赋值给Field实体true，否则赋值false
                    field.setEnums(true);

                    // 以课程等级为例：从注释中的“枚举[CourseLevelEnum]”，得到COURSE_LEVEL
                    int start = comment.indexOf("[");
                    int end = comment.indexOf("]");
                    String enumsName = comment.substring(start + 1, end);
                    String enumsConst = EnumGenerator.toUnderline(enumsName);
                    field.setEnumsConst(enumsConst);
                } else {
                    field.setEnums(false);
                }
                fieldList.add(field);
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("列信息：" + fieldList);
        return fieldList;
    }
    /**
      * description  ：下划线转大驼峰
      * @Param: str
      * @return java.lang.String
      */
    public static String lineToBigHump(String str){
        String s = lineToHump(str);
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }
    /**
      * description  ：下划线转小驼峰
      * @Param: str
      * @return java.lang.String
      */
    public static String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher=linePattern.matcher(str);
        StringBuffer sb=new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**
      * description  ：数据库类型转为Java类型
      * @Param: sqlType
      * @return java.lang.String
      */
    public static String sqlTypeToJavaType(String sqlType){
        if (sqlType.toUpperCase().contains("varchar".toUpperCase())
            ||sqlType.toUpperCase().contains("char".toUpperCase())
            ||sqlType.toUpperCase().contains("text".toUpperCase())){
            return "String";
        }else if (sqlType.toUpperCase().contains("datetime".toUpperCase())){
            return "Date";
        }else if (sqlType.toUpperCase().contains("int".toUpperCase())){
            return "Integer";
        }else if (sqlType.toUpperCase().contains("long".toUpperCase())){
            return "Long";
        }else if (sqlType.toUpperCase().contains("decimal".toUpperCase())){
            return "BigDecimal";
        }else {
            return "String";
        }
    }
}
