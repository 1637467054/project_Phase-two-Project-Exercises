package com.course.common.service;


import com.course.common.dao.ChapterMapper;
import com.course.common.dto.ChapterDto;
import com.course.common.dto.PageDto;
import com.course.common.entity.Chapter;
import com.course.common.entity.ChapterExample;
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

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public void list(PageDto pageDto){
        //                  pageNum:第几页,pageSize:每页多少条
        //pageHelper的使用规则是：调用startPage方法之后，执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ChapterExample chapterExample=new ChapterExample();
        chapterExample.setOrderByClause("id desc");
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        //获取PageInfo对象
        PageInfo<Chapter> pageInfo=new PageInfo<>(chapterList);
        //获取总条数
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        for(int i = 0, l = chapterList.size(); i < l; i++){
            Chapter chapter=chapterList.get(i);
            ChapterDto chapterDto=new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtoList.add(chapterDto);
        }
        pageDto.setList(chapterDtoList);
    }

    public void save(ChapterDto chapterDto){
        Chapter chapter= CopyUtil.copy(chapterDto,Chapter.class);
        if (StringUtils.isEmpty(chapterDto.getId())){
            this.insert(chapter);
        }else {
            this.update(chapter);
        }
    }

    private void insert(Chapter chapter){
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }
    private void update(Chapter chapter){
        //updateByPrimaryKey是对传入字段全部进行跟新,updateByPrimaryKeySelective则是如果传入的为空就不跟新此字段
        chapterMapper.updateByPrimaryKey(chapter);
    }
    public void delete(String id){
        //deleteByPrimaryKey比较坑爹，是主键的字段类型要写成包装类型。比如自增主键，要用 Long 而不是 long
        chapterMapper.deleteByPrimaryKey(id);
    }

}
