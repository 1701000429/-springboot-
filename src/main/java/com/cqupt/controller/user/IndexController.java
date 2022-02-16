package com.cqupt.controller.user;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.domin.Paper;
import com.cqupt.domin.Papertag;
import com.cqupt.domin.Tag;
import com.cqupt.service.PaperService;
import com.cqupt.service.PapertagService;
import com.cqupt.service.TagService;
import com.cqupt.utils.AppFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import com.cqupt.utils.MarkdownUtils;

//普通用户登录后的一些路由
@Controller
@RequestMapping("/cqupt/user")
public class IndexController {

    @Autowired
    PaperService paperService;
    @Autowired
    TagService tagService;
    @Autowired
    PapertagService papertagService;

    //前台论文详情页,之所以再写一遍，没有session会被拦截请求。这里的路径不在cqupt下，不会被拦截
    @GetMapping("/paper/{id}")
    public String paper(@PathVariable Long id, Model model) {
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
        return "paper";
    }


//    @RequestMapping("/paperZipDownload")
//    @ResponseBody
//    public void paperZipDownload(String fileName,
//                                 HttpServletRequest request,
//                                 HttpServletResponse response) throws IOException {
//        if(fileName==null||fileName==""){
//            request.setAttribute("msg","文件下载失败");
//            System.out.println("文件名为空=======");
//            return;
//        }
//        //前端传文件名过来，这里拼接路径
//        String filePath= AppFileUtils.UPLOAD_PATH+"/"+fileName;
//        System.out.println(filePath);
////        File dest = new File(filePath + "\\"+fileName);
//        // 读到流中
//        InputStream inputStream = new FileInputStream(filePath);// 文件的存放路径
//        response.reset();
//        response.setContentType("application/force-download");
//
//
//        String filename = fileName;
//        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
//        ServletOutputStream outputStream = response.getOutputStream();
//        byte[] b = new byte[1024];
//        int len;
//        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
//        while ((len = inputStream.read(b)) > 0) {
//            outputStream.write(b, 0, len);
//        }
//        inputStream.close();
//
//
//    }


    @GetMapping("/myself")
    public String myself() {
        //进入个人主页
        return "myself";

    }
    @GetMapping("/myComment")
    public String myComment() {
        //进入我的评论查看页
        return "redirect:/cqupt/login";

    }


}
