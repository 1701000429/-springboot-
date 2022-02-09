package com.cqupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.domin.Paper;
import com.cqupt.mapper.PaperMapper;
import com.cqupt.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Autowired
    PaperMapper paperDao;

    @Override
    public List<Paper> getAllPaperQuery() {
        return paperDao.getAllPaperQuery();
    }

    @Override
    public Paper getPaperById(Long id) {
        return null;
    }

    @Override
    public int savePaper(Paper Paper) {
        return 0;
    }

    @Override
    public int updatePaper(Paper showPaper) {
        return 0;
    }

    @Override
    public void deletePaper(Long id) {

    }

    @Override
    public List<Paper> getPaperBySearch(Paper searchPaper) {
        return null;
    }

    @Override
    public List<Paper> getAllFirstPagePaper() {
        return null;
    }

    @Override
    public List<Paper> getRecommendedPaper() {
        return null;
    }

    @Override
    public List<Paper> getSearchPaper(String query) {
        return null;
    }

    @Override
    public Paper getDetailedPaper(Long id) {
        return null;
    }

    @Override
    public List<Paper> getByTypeId(Long typeId) {
        return null;
    }

    @Override
    public Integer getPaperTotal() {
        return null;
    }

    @Override
    public Integer getPaperViewTotal() {
        return null;
    }

    @Override
    public Integer getPaperCommentTotal() {
        return null;
    }

    @Override
    public Integer getPaperMessageTotal() {
        return null;
    }
}
