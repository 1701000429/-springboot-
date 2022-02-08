package com.cqupt.controller;
import com.cqupt.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cqupt")
public class MyController {
    //登陆页面
    @GetMapping("/login")
    public String toLogin() {
        return "admin/login";
    }

    ///admin/index 跳转到后台首页
    @GetMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }

    //登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/cqupt/login";
    }

    //跳转到首页，论文列表
    @GetMapping("/paper")
    public String index() {
        return "index";
    }

    //跳转到type分类页面
    @GetMapping("/admin/type")
    public String types() {
        return "admin/types";
    }
}
