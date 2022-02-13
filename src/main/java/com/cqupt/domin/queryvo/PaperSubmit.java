package com.cqupt.domin.queryvo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cqupt.domin.Paper;
import com.cqupt.domin.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-13
 * @描述： 论文上传时提交上来的字段(全)
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PaperSubmit  {
    //新增两个字段，用于接收前端直接传过来的类型和标签。
    public String typename;
    public String tagid;
    //以下内容同Paper

    public static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;
    /**
     * 是否可评论(1可以2不可以)
     */
    public Integer commentabled;

    /**
     * 论文具体内容
     */
    public String content;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date createtime;

    /**
     * 论文描述
     */
    public String description;

    /**
     * 论文首图(存储路径)
     */
    public String firstpicture;

    /**
     * 论文状态(1保存2发布)
     */
    public Integer published;

    /**
     * 论文标题
     */
    public String title;

    /**
     * 论文发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date updatetime;

    /**
     * 论文浏览次数
     */
    public Integer views;

    /**
     * 论文类型id
     */
    public Long typeid;

    /**
     * 论文发布者id
     */
    public Integer userid;

    /**
     * 评论总数
     */
    public Integer commentcount;

    /**
     * 论文对应的附件
     */
    public String zip;

    /**
     * 论文标记
     */
    public String flag;


}
