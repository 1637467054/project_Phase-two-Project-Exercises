package com.course.common.service;


import com.course.common.dao.ChapterMapper;
import com.course.common.dto.ChapterDto;
import com.course.common.entity.Chapter;
import com.course.common.entity.ChapterExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> list(){
        //                  pageNum:第几页,pageSize:每页多少条
        //pageHelper的使用规则是：调用startPage方法之后，执行的第一个select语句会进行分页
        PageHelper.startPage(1,1);
        ChapterExample chapterExample=new ChapterExample();
        chapterExample.setOrderByClause("id desc");
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        for(int i = 0, l = chapterList.size(); i < l; i++){
            Chapter chapter=chapterList.get(i);
            ChapterDto chapterDto=new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
        
    }
}
