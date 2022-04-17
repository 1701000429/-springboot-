package com.cqupt.controller.user;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.domin.Paper;
import com.cqupt.domin.Papertag;
import com.cqupt.domin.Tag;
import com.cqupt.service.PaperService;
import com.cqupt.service.PapertagService;
import com.cqupt.service.TagService;
import com.cqupt.utils.wifiIPV4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import com.cqupt.utils.MarkdownUtils;

//普通用户登录后的一些路由
@Controller
public class localController {

    @Autowired
    PaperService paperService;
    @Autowired
    TagService tagService;
    @Autowired
    PapertagService papertagService;
    //前台论文详情页,之所以再写一遍，没有session会被拦截请求。这里的路径不在cqupt下，不会被拦截
    //前台论文详情页
    @GetMapping("/paper/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Paper paper=paperService.getById(id);
        //浏览量+1
        int nowView=paper.getViews();
        paper.setViews(nowView+1);
        boolean flag=paperService.updateById(paper);
        String paperContent=paper.getContent();
        String HTMLcontent=MarkdownUtils.markdownToHtmlExtensions(paperContent);
        paper.setContent(HTMLcontent);
        model.addAttribute("paper", paper);



        //查询该论文对应的tags，并放进model
        QueryWrapper<Papertag> queryWrapper3 = new QueryWrapper<Papertag>();
        queryWrapper3.eq("paperid",paper.getId());
        List<Papertag> paperTags=papertagService.list(queryWrapper3);
        //现在通过一堆tagid查询tag name
        List<Tag> tags = new ArrayList<Tag>();
        for (Papertag paperTag : paperTags) {
            Tag tag=tagService.getTag((long)paperTag.getTagid());
            System.out.println("查询到的tag========");
            System.out.println(tag);
            tags.add(tag);
        }
        model.addAttribute("tags", tags);
        return "Wechat/paper";
    }

    @GetMapping("/getLocalhost")
    @ResponseBody
    public String getUrl() {
        List<String> ips=wifiIPV4.getLocalIPList();
        System.out.println("服务器IP地址为============");
        System.out.println(ips);
        //我的是第三块IP对应wifi那个
        String ip= ips.get(2);
        return ip;
    }

    @GetMapping("/")
    public String toLogin(){
        return "redirect:/cqupt/login";
    }
}
