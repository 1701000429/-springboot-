package com.cqupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.domin.Paper;
import com.cqupt.domin.Paper;
import com.cqupt.mapper.PaperMapper;
import com.cqupt.mapper.PaperMapper;
import com.cqupt.service.PaperService;
import com.cqupt.service.PaperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PaperTests {
    @Autowired
    PaperMapper mapper;
    @Autowired
    PaperService service;
    //测试mybatisPlus中的service层的接口
    @Test
    void saveTest() {
        Paper paper=new Paper();
        boolean flag=service.updateById(paper);
        System.out.println(flag);
    }

    //使用MybatisPlus自带的分页插件进行分页查询
    @Test
    void selectTest() {
        // 条件分页查询
        //IPage<Paper> page(IPage<Paper> page, Wrapper<Paper> queryWrapper);

        //测试实体类
        Paper Paper=new Paper();
        //1.分页limit条件
        IPage<Paper> page = new Page<Paper>(1,3);
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<Paper>();
        //2.select添加where条件（动态sql）
        //queryWrapper.eq(Paper.getId()!=null,"id",Paper.getId());
        //3.order by
        //queryWrapper.orderByDesc("id");
        //4.调用

        service.page(page,queryWrapper);

        List<Paper> records = page.getRecords();
        System.out.println("result=================");
        System.out.println(records);
    }

}
