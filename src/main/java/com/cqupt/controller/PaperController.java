package com.cqupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
@RestController
@RequestMapping("/bus")
public class PaperController {
    @Autowired
    private PaperService paperService;

//    @RequestMapping("listpaper")
//    public DataGridView loadAllGoods(GoodsVo goodsVo){
//        IPage<Goods> page = new Page<Goods>(goodsVo.getPage(),goodsVo.getLimit());
//        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>();
//        queryWrapper.eq(goodsVo.getProviderid()!=null&&goodsVo.getProviderid()!=0,"providerid",goodsVo.getProviderid());
//        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()),"goodsname",goodsVo.getGoodsname());
//        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()),"productcode",goodsVo.getProductcode());
//        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()),"promitcode",goodsVo.getPromitcode());
//        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()),"description",goodsVo.getDescription());
//        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getSize()),"size",goodsVo.getSize());
//
//        queryWrapper.orderByDesc("id");
//        goodsService.page(page,queryWrapper);
//        List<Goods> records = page.getRecords();
//        for (Goods goods : records) {
//            Provider provider = providerService.getById(goods.getProviderid());
//            if (null!=provider){
//                goods.setProvidername(provider.getProvidername());
//            }
//        }
//        return new DataGridView(page.getTotal(),page.getRecords());
//    }

}
