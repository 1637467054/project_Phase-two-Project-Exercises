package com.course.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @version :
 * @auto :李俨稹
 * @date ：Created in 2020/8/9  15:48
 * @description :这是一个用来分页的实体代码
 * @modified By :V1.0
 */
@Data
public class PageDto<T> {
    /**
     * 当前页码
     */
    protected int page;
    /**
     * 每页条数
     */
    protected int size;
    /**
     * 总条数
     */
    protected long total;
    /**
     * 返回的记录/返回的查询的结果
     */
    protected List<T> list;
}
