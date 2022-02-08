package com.cqupt.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 是否可评论(1可以2不可以)
     */
    private Integer commentabled;

    /**
     * 论文具体内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 论文描述
     */
    private String description;

    /**
     * 论文首图(存储路径)
     */
    private String firstPicture;

    /**
     * 论文状态(1保存2发布)
     */
    private Integer published;

    /**
     * 论文标题
     */
    private String title;

    /**
     * 论文发布时间
     */
    private LocalDateTime updateTime;

    /**
     * 论文浏览次数
     */
    private Integer views;

    /**
     * 论文类型id
     */
    private Long typeId;

    /**
     * 论文发布者id
     */
    private Long userId;

    /**
     * 评论总数
     */
    private Integer commentCount;

    /**
     * 论文对应的附件
     */
    private String zip;

    /**
     * 论文标记
     */
    private String flag;


}
