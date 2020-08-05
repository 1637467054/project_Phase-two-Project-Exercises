package com.course.business.controller;

import com.course.common.entity.Chapter;
import com.course.common.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/7/31  20:35
 * @description :测试类
 * @modified By :
 */
@RestController
public class ChapterController {
    @Resource
    ChapterService chapterService;

//    @GetMapping(value = "/chapter", produces = { "application/json;charset=UTF-8" })
    @RequestMapping("/chapter")
    public List<Chapter> chapter(){
        return chapterService.list();
    }
}
