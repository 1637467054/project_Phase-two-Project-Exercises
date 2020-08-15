package com.course.common.service;


import com.course.common.dao.SectionMapper;
import com.course.common.dto.SectionDto;
import com.course.common.dto.PageDto;
import com.course.common.entity.Section;
import com.course.common.entity.SectionExample;
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
import java.util.Date;

@Service
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;

    public void list(PageDto pageDto){
        //                  pageNum:第几页,pageSize:每页多少条
        //pageHelper的使用规则是：调用startPage方法之后，执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        SectionExample sectionExample=new SectionExample();
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        //获取PageInfo对象
        PageInfo<Section> pageInfo=new PageInfo<>(sectionList);
        //获取总条数
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = new ArrayList<SectionDto>();
        for(int i = 0, l = sectionList.size(); i < l; i++){
            Section section=sectionList.get(i);
            SectionDto sectionDto=new SectionDto();
            BeanUtils.copyProperties(section,sectionDto);
            sectionDtoList.add(sectionDto);
        }
        pageDto.setList(sectionDtoList);
    }

    public void save(SectionDto sectionDto){
        Section section= CopyUtil.copy(sectionDto,Section.class);
        if (StringUtils.isEmpty(sectionDto.getId())){
            this.insert(section);
        }else {
            this.update(section);
        }
    }

    private void insert(Section section){
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }
    private void update(Section section){
                section.setUpdatedAt(new Date());
        //updateByPrimaryKey是对传入字段全部进行跟新,updateByPrimaryKeySelective则是如果传入的为空就不跟新此字段
        sectionMapper.updateByPrimaryKey(section);
    }
    public void delete(String id){
        //deleteByPrimaryKey比较坑爹，是主键的字段类型要写成包装类型。比如自增主键，要用 Long 而不是 long
        sectionMapper.deleteByPrimaryKey(id);
    }

}
