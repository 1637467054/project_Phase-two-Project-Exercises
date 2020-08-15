package com.course.common.service;


import com.course.common.dao.${Domain}Mapper;
import com.course.common.dto.${Domain}Dto;
import com.course.common.dto.PageDto;
import com.course.common.entity.${Domain};
import com.course.common.entity.${Domain}Example;
import com.course.common.util.CopyUtil;
import com.course.common.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
<#list typeSet as type>
    <#if type=="Date">
import java.util.Date;
    </#if>
</#list>

@Service
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    public void list(PageDto pageDto){
        //                  pageNum:第几页,pageSize:每页多少条
        //pageHelper的使用规则是：调用startPage方法之后，执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ${Domain}Example ${domain}Example=new ${Domain}Example();
        <#list fieldList as field>
            <#if field.nameHump=="sort">
        ${domain}Example.setOrderByClause("sort asc");
            </#if>
        </#list>
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        //获取PageInfo对象
        PageInfo<${Domain}> pageInfo=new PageInfo<>(${domain}List);
        //获取总条数
        pageDto.setTotal(pageInfo.getTotal());
        List<${Domain}Dto> ${domain}DtoList = new ArrayList<${Domain}Dto>();
        for(int i = 0, l = ${domain}List.size(); i < l; i++){
            ${Domain} ${domain}=${domain}List.get(i);
            ${Domain}Dto ${domain}Dto=new ${Domain}Dto();
            BeanUtils.copyProperties(${domain},${domain}Dto);
            ${domain}DtoList.add(${domain}Dto);
        }
        pageDto.setList(${domain}DtoList);
    }

    public void save(${Domain}Dto ${domain}Dto){
        ${Domain} ${domain}= CopyUtil.copy(${domain}Dto,${Domain}.class);
        if (StringUtils.isEmpty(${domain}Dto.getId())){
            this.insert(${domain});
        }else {
            this.update(${domain});
        }
    }

    private void insert(${Domain} ${domain}){
        Date now = new Date();
        <#list fieldList as field>
            <#if field.nameHump=='createdAt'>
        ${domain}.setCreatedAt(now);
            </#if>
            <#if field.nameHump=='updatedAt'>
        ${domain}.setUpdatedAt(now);
            </#if>
        </#list>
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }
    private void update(${Domain} ${domain}){
        <#list fieldList as field>
            <#if field.nameHump=='updatedAt'>
                ${domain}.setUpdatedAt(new Date());
            </#if>
        </#list>
        //updateByPrimaryKey是对传入字段全部进行跟新,updateByPrimaryKeySelective则是如果传入的为空就不跟新此字段
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }
    public void delete(String id){
        //deleteByPrimaryKey比较坑爹，是主键的字段类型要写成包装类型。比如自增主键，要用 Long 而不是 long
        ${domain}Mapper.deleteByPrimaryKey(id);
    }

}
