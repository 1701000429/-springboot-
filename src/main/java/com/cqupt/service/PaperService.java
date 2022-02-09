package com.cqupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.domin.Paper;
import com.cqupt.domin.queryvo.PaperQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
public interface PaperService extends IService<Paper> {

    //获取全部paper，后台列表显示
    List<PaperQuery> getAllPaperQuery();

    Paper getPaperById(Long id);



    int savePaper(Paper Paper);

    int updatePaper(Paper showPaper);

    void deletePaper(Long id);

    List<Paper> getPaperBySearch(Paper searchPaper);

    List<Paper> getAllFirstPagePaper();

    List<Paper> getRecommendedPaper();

//    List<FirstPagePaper> getNewPaper();

    List<Paper> getSearchPaper(String query);

    Paper getDetailedPaper(Long id);

    //根据TypeId获取博客列表，在分类页进行的操作
    List<Paper> getByTypeId(Long typeId);

    Integer getPaperTotal();

    Integer getPaperViewTotal();

    Integer getPaperCommentTotal();

    Integer getPaperMessageTotal();
}
