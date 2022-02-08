package com.cqupt.controller;


import com.cqupt.service.BusGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-07
 */
@Controller
//@RequestMapping("/")
public class BusGoodsController {

    @Autowired
    private BusGoodsService busGoodsService;

    @RequestMapping("/")
    public String test(){
//        int a=3/0;
        return "CQUPTtest";
    }
}
