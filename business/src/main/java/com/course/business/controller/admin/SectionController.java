package com.course.business.controller.admin;

import com.course.common.dto.SectionDto;
import com.course.common.dto.PageDto;
import com.course.common.dto.ResponseDto;
import com.course.common.exception.ValidatorException;
import com.course.common.service.SectionService;
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
@RequestMapping("/admin/section")
public class SectionController {
    @Resource
    SectionService sectionService;
    public static final String BUSINESS_NAME="小节";

//    @GetMapping(value = "/section", produces = { "application/json;charset=UTF-8" })
    @PostMapping("list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        sectionService.list(pageDto);
        ResponseDto<PageDto> responseDto=new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("save")
    public ResponseDto save(@RequestBody SectionDto sectionDto){
        ValidatorUtil.require(sectionDto.getCourseId(),"课程ID");
        ValidatorUtil.length(sectionDto.getCourseId(),"课程ID",1,8);
        System.out.println(sectionDto);
        sectionService.save(sectionDto);
        ResponseDto<SectionDto> responseDto=new ResponseDto<>();
        responseDto.setContent(sectionDto);
        return responseDto;
    }
//    @DeleteMapping("/delete/{id}/{name}")
    @DeleteMapping("/delete/{id}")
//    public ResponseDto delete(@PathVariable String id,@PathVariable String name){
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto=new ResponseDto();
        sectionService.delete(id);
        return responseDto;
    }
}
