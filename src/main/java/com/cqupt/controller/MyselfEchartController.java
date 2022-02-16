package com.cqupt.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.domin.Paper;
import com.cqupt.domin.Tag;
import com.cqupt.domin.Type;
import com.cqupt.domin.User;
import com.cqupt.domin.queryvo.PaperQuery;
import com.cqupt.service.PaperService;
import com.cqupt.service.TagService;
import com.cqupt.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


//放一些个人中心   表格统计 简单路由
@Controller
@RequestMapping("/cqupt")
public class MyselfEchartController {
    @GetMapping("/admin/echart")
    public String echats() {
        return "admin/echart";
    }

    @GetMapping("/admin/myself")
    public String AdminMyself() {
        return "admin/myself";
    }



}
