package com.cqupt.controller;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.domin.Paper;
import com.cqupt.domin.Tag;
import com.cqupt.domin.Type;
import com.cqupt.domin.User;
import com.cqupt.domin.queryvo.PaperQuery;
import com.cqupt.service.PaperService;
import com.cqupt.service.TagService;
import com.cqupt.service.TypeService;
import com.cqupt.service.UserService;
import com.cqupt.utils.ResultObj;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


//放一些登录，不同级别用户登陆成功后的一些简单路由
@Controller
@RequestMapping("/cqupt")
public class MyController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;
//    commenthistory

    @GetMapping("/user/commentHistory")
    public String commenthistory() {

        return "commenthistory";
    }

    @GetMapping("/admin/loginInfo")
    public String loginInfo() {
        return "admin/logininfo";
    }

    //历史记录简单路由
    @GetMapping("/user/history")
    public String history() {
        return "history";
    }
    //注册页面
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }


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

    //跳转到用户首页，论文列表
    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                        RedirectAttributes attributes,
                        HttpSession session){
        System.out.println("user================================");
        User LoginUser=(User) session.getAttribute("user");
        System.out.println(LoginUser);
        System.out.println("user================================");
        //查询一些type和tag供前端展示
        QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>();
        queryWrapper.last(" ORDER BY id  DESC LIMIT 6");
        List<Type> types=typeService.list(queryWrapper);
        QueryWrapper<Tag> queryWrapper2 = new QueryWrapper<Tag>();
        queryWrapper2.last(" ORDER BY id  DESC LIMIT 10");
        List<Tag> tags=tagService.list(queryWrapper2);
        //查询一些推荐论文供前端展示(这里就简单的推荐最新发表的)
        QueryWrapper<Paper> queryWrapper3 = new QueryWrapper<Paper>();
        queryWrapper3.last(" ORDER BY createtime  DESC LIMIT 5");
        List<Paper> recommendPapers=paperService.list(queryWrapper3);
        //将这些对象传给model
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
        model.addAttribute("recommendPapers", recommendPapers);
        User LoginUser2=(User) session.getAttribute("user");
        model.addAttribute("USER", LoginUser2);
        //查询全部paper缩略
        //按照排序字段 倒序 排序
        String orderBy = " updatetime desc";
        PageHelper.startPage(pageNum,6,orderBy);
        List<PaperQuery> list = paperService.getAllPaperQuery();
        System.out.println("zip是否存在 ");
        System.out.println(list);
        PageInfo<PaperQuery> pageInfo = new PageInfo<PaperQuery>(list);
        model.addAttribute("pageInfo",pageInfo);



        //more
        List<Type> ALLtypes=typeService.list();
        List<Tag> ALLtags=tagService.list();
        model.addAttribute("ALLtypes", ALLtypes);
        model.addAttribute("ALLtags", ALLtags);
        return "index";
    }


}
