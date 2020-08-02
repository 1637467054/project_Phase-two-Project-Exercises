package com.course.system.controller;

import com.course.common.entity.Test;
import com.course.common.service.TestService;
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
public class TestController {
    @Resource
    TestService testService;

//    @GetMapping(value = "/test", produces = { "application/json;charset=UTF-8" })
    @RequestMapping("/test")
    public List<Test> test(){
        return testService.list();
    }
}
