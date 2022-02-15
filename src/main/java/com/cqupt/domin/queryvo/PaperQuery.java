package com.cqupt.domin.queryvo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cqupt.domin.Type;
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
 * @since 2022-02-08
 * @描述： 后台首页展示的缩略    前台首页展示的缩略
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PaperQuery {
    //以下是后台页面展示的论文列表页

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //论文标题
    private String title;
    //论文更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatetime;
    //论文类型
    private Integer typeid;
    private String name;
    private Type type;
    //论文浏览量
    private Integer views;

    //add in 2022/2/15 适配前台，加一个descriiption
    /**
     * 论文描述
     */
    private String description;
}
