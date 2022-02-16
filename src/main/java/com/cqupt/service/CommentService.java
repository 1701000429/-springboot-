package com.cqupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.domin.Comment;
import com.cqupt.domin.queryvo.CommentQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-16
 */
public interface CommentService extends IService<Comment> {
     List<Comment> getByPaperId(long paperId);

     List<CommentQuery> getByPaperIdAndParent(long paperId);
}
