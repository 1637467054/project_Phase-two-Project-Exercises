package com.course.business.controller.admin;

import com.course.common.dto.CourseDto;
import com.course.common.dto.PageDto;
import com.course.common.dto.ResponseDto;
import com.course.common.service.CourseService;
import com.course.common.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/7/31  20:35
 * @description :测试类
 * @modified By :
 */
@RestController
@RequestMapping("/admin/course")
public class CourseController {
    @Resource
    CourseService courseService;
    public static final String BUSINESS_NAME="";

//    @GetMapping(value = "/course", produces = { "application/json;charset=UTF-8" })
    @PostMapping("list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        courseService.list(pageDto);
        ResponseDto<PageDto> responseDto=new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("save")
    public ResponseDto save(@RequestBody CourseDto courseDto){
        //保存校验
        ValidatorUtil.require(courseDto.getName(),"名称");
        ValidatorUtil.length(courseDto.getName(),"名称",1,50);
        ValidatorUtil.length(courseDto.getSummary(),"概述",1,2000);
        ValidatorUtil.length(courseDto.getImage(),"封面",1,100);
        System.out.println(courseDto);
        courseService.save(courseDto);
        ResponseDto<CourseDto> responseDto=new ResponseDto<>();
        responseDto.setContent(courseDto);
        return responseDto;
    }
//    @DeleteMapping("/delete/{id}/{name}")
    @DeleteMapping("/delete/{id}")
//    public ResponseDto delete(@PathVariable String id,@PathVariable String name){
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto=new ResponseDto();
        courseService.delete(id);
        return responseDto;
    }
}
