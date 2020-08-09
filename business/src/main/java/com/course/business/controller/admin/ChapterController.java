package com.course.business.controller.admin;

import com.course.common.dto.PageDto;
import com.course.common.service.ChapterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/7/31  20:35
 * @description :测试类
 * @modified By :
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    ChapterService chapterService;

//    @GetMapping(value = "/chapter", produces = { "application/json;charset=UTF-8" })
    @RequestMapping("list")
    public PageDto list(@RequestBody PageDto pageDto){
        chapterService.list(pageDto);
        return pageDto;
    }
}
