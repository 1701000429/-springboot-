package com.cqupt.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 刘博文
 * @since 2022-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Commenthistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 评论者用户名
     */
    private String username;

    /**
     * 评论具体内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date commenttime;


}
