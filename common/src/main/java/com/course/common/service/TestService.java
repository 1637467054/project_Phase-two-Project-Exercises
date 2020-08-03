package com.course.common.service;


import com.course.common.dao.TestMapper;
import com.course.common.entity.Test;
import com.course.common.entity.TestExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        TestExample testExample=new TestExample();
        testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    }
}
