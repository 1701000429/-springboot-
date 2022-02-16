package com.cqupt.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cqupt.domin.queryvo.CommentQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    //构造函数
    public Comment(){

    }

    public Comment(Long id, String nickname, String email, String content, String avatar, Date createtime, Long paperid, Long parentcommentid, Integer admincomment) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.createtime = createtime;
        this.paperid = paperid;
        this.parentcommentid = parentcommentid;
        this.admincomment = admincomment;
    }



    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 评论者昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 评论关联的论文id
     */
    private Long paperid;

    /**
     * 父id
     */
    private Long parentcommentid;

    /**
     * 管理员评论(1表示是)
     */
    private Integer admincomment;


}
