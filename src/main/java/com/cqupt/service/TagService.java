package com.cqupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.domin.Tag;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
public interface TagService extends IService<Tag> {

    int saveTag(Tag Tag);

    Tag getTag(Long id);

    List<Tag> getAllTag();

    List<Tag>getAllTagAndPaper();

    Tag getTagByName(String name);

    int updateTag(Tag Tag);

    void deleteTag(Long id);
}
