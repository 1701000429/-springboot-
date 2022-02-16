package com.cqupt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.domin.Comment;
import com.cqupt.domin.queryvo.CommentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-16
 */
public interface CommentMapper extends BaseMapper<Comment> {


    List<Comment> getByPaperId(@Param("paperid") Long paperid);

    //通过paperId 和 parentcommentid  获取列表
    List<Comment> getByPaperIdAndParentId(@Param("paperid") Long paperid,
                                          @Param("parentcommentid") Long parentcommentid);

}
