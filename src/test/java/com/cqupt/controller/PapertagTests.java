package com.cqupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.domin.Papertag;
import com.cqupt.mapper.PapertagMapper;
import com.cqupt.service.PapertagService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.domin.Papertag;

        import org.junit.jupiter.api.Test;
        import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PapertagTests {

    @Autowired
    PapertagMapper papertagMapper;
    @Autowired
    PapertagService papertagService;

    //测试mybatisPlus中的service层的接口
    @Test
    void saveTest() {
        Papertag papertag=new Papertag(2,10);
        System.out.println(papertag);
        boolean flag=papertagService.save(papertag);
        System.out.println(flag);
    }

    //使用MybatisPlus自带的分页插件进行分页查询
    @Test
    void selectTest() {
        // 条件分页查询
        //IPage<Papertag> page(IPage<Papertag> page, Wrapper<Papertag> queryWrapper);

        //测试实体类
        Papertag papertag=new Papertag(1,1,10);
        //1.分页limit条件
        IPage<Papertag> page = new Page<Papertag>(1,3);
        QueryWrapper<Papertag> queryWrapper = new QueryWrapper<Papertag>();
        //2.select添加where条件（动态sql）
        //queryWrapper.eq(papertag.getId()!=null,"id",papertag.getId());
        //3.order by
        queryWrapper.orderByDesc("id");
        //4.调用
        papertagService.page(page,queryWrapper);
        List<Papertag> records = page.getRecords();
        System.out.println("result=================");
        System.out.println(records);
    }

    //修改
    @Test
    void updateTest(){
        //boolean updateById(T entity);
        //测试实体类
        Papertag papertag=new Papertag(1,1,15);
        boolean flag=papertagService.updateById(papertag);
        System.out.println("修改情况：");
        System.out.println(flag);
    }

    //删除
    @Test
    void deleteTest(){
        //int deleteById(Serializable id);
        //测试实体类

        int flag=papertagMapper.deleteById(1);
        System.out.println("删除情况：");
        System.out.println(flag);
    }


    //测试自己写的

}
