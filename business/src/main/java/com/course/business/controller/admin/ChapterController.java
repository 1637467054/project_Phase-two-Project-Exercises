package com.course.business.controller.admin;

import com.course.common.dto.ChapterDto;
import com.course.common.dto.PageDto;
import com.course.common.dto.ResponseDto;
import com.course.common.exception.ValidatorException;
import com.course.common.service.ChapterService;
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
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    ChapterService chapterService;
    public static final String BUSINESS_NAME="大章";

//    @GetMapping(value = "/chapter", produces = { "application/json;charset=UTF-8" })
    @PostMapping("list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        chapterService.list(pageDto);
        ResponseDto<PageDto> responseDto=new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        ValidatorUtil.require(chapterDto.getName(),"名称");
        ValidatorUtil.require(chapterDto.getCourseId(),"课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(),"课程ID",1,8);
        System.out.println(chapterDto);
        chapterService.save(chapterDto);
        ResponseDto<ChapterDto> responseDto=new ResponseDto<>();
        responseDto.setContent(chapterDto);
        return responseDto;
    }
//    @DeleteMapping("/delete/{id}/{name}")
    @DeleteMapping("/delete/{id}")
//    public ResponseDto delete(@PathVariable String id,@PathVariable String name){
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto=new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }
}
