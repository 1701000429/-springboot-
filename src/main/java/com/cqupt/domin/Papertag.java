package com.cqupt.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Papertag implements Serializable {

    private static final long serialVersionUID = 1L;

    public Papertag(Integer id,Integer paperid, Integer tagid) {
        this.id=id;
        this.paperid = paperid;
        this.tagid = tagid;
    }

    private Integer id;

    /**
     * 论文id
     */

    private Integer paperid;

    /**
     * 标签id(采用联合主键)
     */

    private Integer tagid;


}
