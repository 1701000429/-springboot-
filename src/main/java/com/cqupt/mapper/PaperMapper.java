package com.cqupt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.domin.Paper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
public interface PaperMapper extends BaseMapper<Paper> {


    List<Paper> getAllPaper();


    //todo

    Paper getPaperById(Long id);



    List<Paper> getAllPaperQuery();

    int savePaper(Paper Paper);

    int updatePaper(Paper showPaper);

    void deletePaper(Long id);

    List<Paper> searchByTitleOrTypeOrRecommend(Paper searchPaper);

    List<Paper> getFirstPagePaper();

    List<Paper> getAllRecommendPaper();

//    List<FirstPagePaper> getNewPaper();

    List<Paper> getSearchPaper(String query);

    Paper getDetailedPaper(Long id);

    int updateViews(Long id);

    //    根据博客id查询出评论数量
    int getCommentCountById(Long id);

    List<Paper> getByTypeId(Long typeId);

    Integer getPaperTotal();

    Integer getPaperViewTotal();

    Integer getPaperCommentTotal();

    Integer getPaperMessageTotal();

}
