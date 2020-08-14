package com.course.common.dto;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class SectionDto {
    /**
     * ID
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 课程|course.id
     */
    private String courseId;
    /**
     * 大章|chapter.id
     */
    private String chapterId;
    /**
     * 视频
     */
    private String video;
    /**
     * 顺序
     */
    private Integer time;
    /**
     * 收费|C 收费;F 免费
     */
    private String charge;
    /**
     * 顺序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdAt;
    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedAt;

}