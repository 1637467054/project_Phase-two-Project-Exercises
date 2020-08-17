package com.course.common.service;


import com.course.common.dao.CourseMapper;
import com.course.common.dto.CourseDto;
import com.course.common.dto.PageDto;
import com.course.common.entity.Course;
import com.course.common.entity.CourseExample;
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
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    public void list(PageDto pageDto){
        //                  pageNum:第几页,pageSize:每页多少条
        //pageHelper的使用规则是：调用startPage方法之后，执行的第一个select语句会进行分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample=new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        //获取PageInfo对象
        PageInfo<Course> pageInfo=new PageInfo<>(courseList);
        //获取总条数
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
        for(int i = 0, l = courseList.size(); i < l; i++){
            Course course=courseList.get(i);
            CourseDto courseDto=new CourseDto();
            BeanUtils.copyProperties(course,courseDto);
            courseDtoList.add(courseDto);
        }
        pageDto.setList(courseDtoList);
    }

    public void save(CourseDto courseDto){
        Course course= CopyUtil.copy(courseDto,Course.class);
        if (StringUtils.isEmpty(courseDto.getId())){
            this.insert(course);
        }else {
            this.update(course);
        }
    }

    private void insert(Course course){
        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }
    private void update(Course course){
                course.setUpdatedAt(new Date());
        //updateByPrimaryKey是对传入字段全部进行跟新,updateByPrimaryKeySelective则是如果传入的为空就不跟新此字段
        courseMapper.updateByPrimaryKey(course);
    }
    public void delete(String id){
        //deleteByPrimaryKey比较坑爹，是主键的字段类型要写成包装类型。比如自增主键，要用 Long 而不是 long
        courseMapper.deleteByPrimaryKey(id);
    }

}
