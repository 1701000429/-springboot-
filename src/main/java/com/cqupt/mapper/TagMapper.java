package com.cqupt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.domin.Tag;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
public interface TagMapper extends BaseMapper<Tag> {
    int saveTag(Tag Tag);

    Tag getTag(Long id);

    List<Tag> getAllTag();

    List<Tag> getAllTagAndPaper();

    Tag getTagByName(String name);

    int updateTag(Tag Tag);

    void deleteTag(Long id);
}
