package com.cqupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.domin.Tag;
import com.cqupt.mapper.TagMapper;
import com.cqupt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagMapper TagDao;



    @Transactional
    @Override
    public int saveTag(Tag Tag) {
        return TagDao.saveTag(Tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return TagDao.getTag(id);
    }

    @Transactional
    @Override
    public List<Tag> getAllTag() {
        return TagDao.getAllTag();
    }

    @Override
    public List<Tag> getAllTagAndPaper() {
        return TagDao.getAllTagAndPaper();
    }

    @Override
    public Tag getTagByName(String name) {
        return TagDao.getTagByName(name);
    }

    @Transactional
    @Override
    public int updateTag(Tag Tag) {
        return TagDao.updateTag(Tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        TagDao.deleteTag(id);
    }

}
