package com.cqupt.controller.user;
import com.cqupt.domin.Paper;
import com.cqupt.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


//普通用户登录后的一些路由
@Controller
@RequestMapping("/cqupt/user")
public class IndexController {

    @Autowired
    PaperService paperService;

    //前台论文详情页
    @GetMapping("/paper/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Paper paper=paperService.getById(id);
        model.addAttribute("paper", paper);
        return "paper";
    }

    @GetMapping("/paperZipDownload222222222222")
    public String paperZipDownload() {
        //论文下载
        return "redirect:/cqupt/login";

    }

}
