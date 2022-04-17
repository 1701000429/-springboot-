package com.cqupt.controller.user;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.domin.*;
import com.cqupt.domin.queryvo.PaperQuery;
import com.cqupt.mapper.TypeMapper;
import com.cqupt.service.*;
import com.cqupt.utils.AppFileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.Date;
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
    TypeService typeService;
    @Autowired
    PapertagService papertagService;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    PaperuserService paperuserService;


    @GetMapping("/paper/{id}")
    public String paper(@PathVariable Long id, Model model,HttpSession session) {
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

        //2022/4/17 最新提交   这里把用户浏览信息记录到数据库
        User userTemp=(User)session.getAttribute("user");
        Paperuser paperuser=new Paperuser();
        paperuser.setPapername(paper.getTitle());
        paperuser.setUsername(userTemp.getUsername());
        paperuser.setReadtime(new Date());
        paperuserService.save(paperuser);


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


    //模糊查询
    @PostMapping("/paperSelect")
    public String myComment(String selectType,String selectContent,
                            Model model,HttpSession session,
                            @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        //检查参数传递是否正确
        System.out.println("user select================================");
        System.out.println(selectType);
        System.out.println(selectContent);


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


        //根据用户传递的查询条件进行模糊查询
        List<Paper> returnPaperlist=null;
        QueryWrapper<Paper> queryWrapper4 = new QueryWrapper<Paper>();
        if(selectType.equals("论文名称")){
            queryWrapper4.like("title",selectContent);
            returnPaperlist = paperService.list(queryWrapper4);
        }
        else if(selectType.equals("论文类型")){
            //先模糊查询出该selectContent对应的typeid
            Type typeLikeName = typeMapper.getTypeByName(selectContent);
            if(typeLikeName!=null){
                Integer typeIdget=typeLikeName.getId();
                queryWrapper4.eq("typeid",typeIdget);
                returnPaperlist = paperService.list(queryWrapper4);
            }

        }
        else if(selectType.equals("论文标签")){
            Tag tagByName = tagService.getTagByName(selectContent);
            if(tagByName!=null){
                Integer tagid = tagByName.getId();
                //查询papertag关系表中 所有tagid的paper
                QueryWrapper<Papertag> queryWrapper5 = new QueryWrapper<Papertag>();
                queryWrapper5.eq("tagid",tagid);
                List<Papertag> paperWantId = papertagService.list(queryWrapper5);
                //根据paperId
                Papertag papertagTemp=null;
                if(paperWantId!=null&&paperWantId.size()!=0){
                    for (Papertag papertag : paperWantId) {
                        papertagTemp=papertag;
                        queryWrapper4.eq("id",papertag.getPaperid()).or();
                    }
                    queryWrapper4.eq("id",papertagTemp.getPaperid());
                    returnPaperlist = paperService.list(queryWrapper4);
                }


            }

        }


        //returnPaperlist 是应该返回的paper，转化成PaperQuery
        List<PaperQuery> paperQueryList= new ArrayList<>();

        if(returnPaperlist!=null&&returnPaperlist.size()!=0){
            for (Paper paper : returnPaperlist) {
                PaperQuery temp=new PaperQuery(paper);
                //为type typeName 赋值
                Type typeTemp=typeService.getById(paper.getTypeid());
                temp.setType(typeTemp);
                temp.setName(typeTemp.getName());
                paperQueryList.add(temp);
            }
        }

        System.out.println("查询后返回-=--==-==-=");
        System.out.println(paperQueryList);
        //至此 把paperQueryList 分页然后返回给前端
        //String orderBy = " updatetime desc";
        PageHelper.startPage(pageNum,10);

        PageInfo<PaperQuery> pageInfo = new PageInfo<PaperQuery>(paperQueryList);
        model.addAttribute("pageInfo",pageInfo);
//more
        List<Type> ALLtypes=typeService.list();
        List<Tag> ALLtags=tagService.list();
        model.addAttribute("ALLtypes", ALLtypes);
        model.addAttribute("ALLtags", ALLtags);
        //进入首页
        return "index";

    }
}
