package com.cqupt.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 头像(存储路径)
     */
    private String avatar;

    /**
     * 用户创建时间
     */
    private Date createtime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型(1用户，2管理员)
     */
    private Integer type;

    /**
     * 用户信息更新时间
     */
    private Date updatetime;

    /**
     * 登录名
     */
    private String username;


}
