package com.cqupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.domin.Paper;
import com.cqupt.domin.Tag;
import com.cqupt.domin.Type;
import com.cqupt.domin.User;
import com.cqupt.domin.queryvo.PaperQuery;
import com.cqupt.service.PaperService;
import com.cqupt.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
@Controller
@RequestMapping("/cqupt/admin")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private TypeService typeService;

    //论文列表
    @RequestMapping("/papers")
    public String papers(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = " updatetime desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<PaperQuery> list = paperService.getAllPaperQuery();

        System.out.println("数据库中查询到的paperlist缩略如下=====:");
        System.out.println(list);
        System.out.println("数据库中查询到的paperlist缩略如下=====:");
        PageInfo<PaperQuery> pageInfo = new PageInfo<PaperQuery>(list);
        model.addAttribute("types",typeService.getAllType());

        model.addAttribute("pageInfo",pageInfo);
        System.out.println("数据库中查询到的paper缩略如下=====:");
        System.out.println(pageInfo);
        System.out.println("数据库中查询到的paper缩略如下=====:");
        return "admin/papers";
    }


    //todo  2022/2/10


    //跳转论文新增页面
    @GetMapping("/papers/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("paper", new Paper());
        return "admin/papers-input";
    }

//    // 论文新增
    @PostMapping("/papers")
    public String post(Paper paper,
                       RedirectAttributes attributes,
                       HttpSession session){
        System.out.println("进入论文新增的controller=====");
        System.out.println(paper);
        //id commentabled content  description firstpicture title
        System.out.println("进入论文新增的controller=====");
        User LoginUser=(User) session.getAttribute("user");
        //设置用户id，从session里面拿
        paper.setUserid(LoginUser.getId());
        paper.setViews(0);
        paper.setCreatetime(new Date());
        paper.setUpdatetime(new Date());
        paper.setFlag(null);
        paper.setCommentcount(0);
        //todo   论文首图，zip的上传

        //这里使用MybatisPlus提供的API
        boolean successFlag=paperService.save(paper);
        if(successFlag==false){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/cqupt/admin/papers";
    }

//    //    删除文章
//    @GetMapping("/blogs/{id}/delete")
//    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
//        blogService.deleteBlog(id);
//        attributes.addFlashAttribute("message", "删除成功");
//        return "redirect:/admin/blogs";
//    }
//
//    //    跳转编辑修改文章
//    @GetMapping("/blogs/{id}/input")
//    public String editInput(@PathVariable Long id, Model model) {
//        ShowBlog blogById = blogService.getBlogById(id);
//        List<Type> allType = typeService.getAllType();
//        model.addAttribute("blog", blogById);
//        model.addAttribute("types", allType);
//        return "admin/blogs-input";
//    }
//
//    //    编辑修改文章
//    @PostMapping("/blogs/{id}")
//    public String editPost(@Valid ShowBlog showBlog, RedirectAttributes attributes) {
//        int b = blogService.updateBlog(showBlog);
//        if(b ==0){
//            attributes.addFlashAttribute("message", "修改失败");
//        }else {
//            attributes.addFlashAttribute("message", "修改成功");
//        }
//        return "redirect:/admin/blogs";
//    }
//
//    //    搜索博客
//    @PostMapping("/blogs/search")
//    public String search(SearchBlog searchBlog, Model model,
//                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
//        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
//        PageHelper.startPage(pageNum, 10);
//        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
//        model.addAttribute("pageInfo", pageInfo);
//        return "admin/blogs :: blogList";
//    }

}
