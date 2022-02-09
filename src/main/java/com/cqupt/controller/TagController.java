package com.cqupt.controller;


import com.cqupt.domin.Tag;
import com.cqupt.service.TagService;
import com.cqupt.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 标签管理控制器
 * @Author: LBW
 */
@Controller
@RequestMapping("/cqupt/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //    分页查询分类列表
    @GetMapping("/tags")
    public String list(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        System.out.println("进入分类页面，参数:");
        System.out.println(pageNum);
        String orderBy = " id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Tag> list = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(list);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //    返回新增分类页面
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    //  新增分类
    @PostMapping("/tags")
    public String post(@Valid Tag Tag, RedirectAttributes attributes) {
        Tag Tag1 = tagService.getTagByName(Tag.getName());
        if (Tag1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/cqupt/admin/tags/input";
        }
        int t = tagService.saveTag(Tag);
        if (t == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/cqupt/admin/tags";
    }

    //    跳转修改分类页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    //    编辑修改分类
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag Tag, RedirectAttributes attributes) {
        Tag Tag1 = tagService.getTagByName(Tag.getName());
        if (Tag1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/cqupt/admin/tags/input";
        }
        int t = tagService.updateTag(Tag);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/cqupt/admin/tags";
    }

    //    删除分类
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/cqupt/admin/tags";
    }
}