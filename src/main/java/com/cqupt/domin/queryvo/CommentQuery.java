package com.cqupt.domin.queryvo;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cqupt.domin.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentQuery implements Serializable {

    public CommentQuery(Comment commentQuery) {
        this.id = commentQuery.getId();
        this.nickname = commentQuery.getNickname();
        this.email = commentQuery.getEmail();
        this.content = commentQuery.getContent();
        this.avatar = commentQuery.getAvatar();
        this.createtime = commentQuery.getCreatetime();
        this.paperid = commentQuery.getPaperid();
        this.parentcommentid = commentQuery.getParentcommentid();
        this.admincomment = commentQuery.getAdmincomment();

    }
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;
    private String email;
    private String content;

    //头像
    private String avatar;
    private Date createtime;

    private Long paperid;
    //-1表示一级评论   不为-1表示他是子评论
    private Long parentcommentid;
    private String parentnickname;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private CommentQuery parentComment;
    //1表示是管理员的评论
    private int admincomment;

    private PaperQuery paperQuery;



}
