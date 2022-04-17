package com.cqupt.controller.user;


import com.cqupt.domin.Comment;
import com.cqupt.domin.Commenthistory;
import com.cqupt.domin.User;
import com.cqupt.domin.queryvo.CommentQuery;
import com.cqupt.mapper.CommentMapper;
import com.cqupt.service.CommentService;
import com.cqupt.service.CommenthistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-16
 */
@Controller
@RequestMapping("/cqupt")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;
    //默认头像
    @Autowired
    private CommenthistoryService commentHistoryService;
    @Value("${comment.avatar}")
    private String avatar;

    //这是一个对应ajax的异步刷新的控制器，把内容返回给paper.html中的<div th:fragment="commentList">
    @GetMapping("/comments/{paperid}")
    public String comments(@PathVariable Long paperid, Model model) {
        System.out.println("进入comments 查询  结果为=========");
        System.out.println(commentService.getByPaperId(paperid));
        // 使用这种方式进行查询的话  comment没有层级关系，因此需要给前端一个commentQuery
        model.addAttribute("comments", commentService.getByPaperIdAndParent(paperid));
        return "paper :: commentList";
    }

    //处理前端的ajax  post新增评论请求
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long paperId = comment.getPaperid();

        User user = (User) session.getAttribute("user");
        //判断用户是管理员还是普通用户
        if (user != null) {
            if(user.getType()==2)
                comment.setAdmincomment(1);
            else
                comment.setAdmincomment(0);
        }
        //判断用户有没有头像
        if(user.getAvatar()!=null){
            comment.setAvatar(user.getAvatar());
        }else {
            //默认头像TODO
            comment.setAvatar(avatar);
        }

        //评论的持久化
        commentMapper.insert(comment);

        //2022 4 18 新增 评论历史
        Commenthistory commenthistory=new Commenthistory();
        commenthistory.setCommenttime(new Date());
        commenthistory.setUsername(user.getUsername());
        commenthistory.setContent(comment.getContent());
        commentHistoryService.save(commenthistory);


        return "redirect:/comments/" + paperId;
    }
}
