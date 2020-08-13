package com.course.${module}.controller.admin;

import com.course.common.dto.${Domain}Dto;
import com.course.common.dto.PageDto;
import com.course.common.dto.ResponseDto;
import com.course.common.exception.ValidatorException;
import com.course.common.service.${Domain}Service;
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
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {
    @Resource
    ${Domain}Service ${domain}Service;
    public static final String BUSINESS_NAME="${tableNameCn}";

//    @GetMapping(value = "/${domain}", produces = { "application/json;charset=UTF-8" })
    @PostMapping("list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ${domain}Service.list(pageDto);
        ResponseDto<PageDto> responseDto=new ResponseDto<>();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto){
        ValidatorUtil.require(${domain}Dto.getName(),"名称");
        ValidatorUtil.require(${domain}Dto.getCourseId(),"课程ID");
        ValidatorUtil.length(${domain}Dto.getCourseId(),"课程ID",1,8);
        System.out.println(${domain}Dto);
        ${domain}Service.save(${domain}Dto);
        ResponseDto<${Domain}Dto> responseDto=new ResponseDto<>();
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }
//    @DeleteMapping("/delete/{id}/{name}")
    @DeleteMapping("/delete/{id}")
//    public ResponseDto delete(@PathVariable String id,@PathVariable String name){
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto=new ResponseDto();
        ${domain}Service.delete(id);
        return responseDto;
    }
}
