package com.cqupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.domin.Comment;
import com.cqupt.domin.queryvo.CommentQuery;
import com.cqupt.mapper.CommentMapper;
import com.cqupt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> getByPaperId(long paperId) {

        //直接这么返回是没有层级关系的，这里做个处理，将其处理为有层级关系的CommentQuery
        return commentMapper.getByPaperId(paperId);
    }

    @Override
    public List<CommentQuery> getByPaperIdAndParent(long paperId) {
        List<CommentQuery> commentQueryList=new ArrayList<>();

        List<Comment> comments=commentMapper.getByPaperIdAndParentId(paperId, (long) -1);
        //转换类型
        for (Comment comment : comments) {
            CommentQuery commentQuery=new CommentQuery(comment);
            commentQueryList.add(commentQuery);
        }
        //遍历父评论 手动拼接子评论（只支持两级评论显示）
        for (CommentQuery comment : commentQueryList) {
            long parentId=comment.getId();
            //看看这个id下有没有孩子
            List<Comment> commentsChild=commentMapper.getByPaperIdAndParentId(paperId,parentId);
            if(!commentsChild.isEmpty()){
                comment.setReplyComments(commentsChild);
            }
//            //他的孙子也加进来，因为两级显示
//            while(!commentsChild.isEmpty()){
//                for (Comment comment1 : commentsChild) {
//                    //孙子列表
//                    List<Comment> commentsChild2=commentMapper.getByPaperIdAndParentId(paperId,comment1.getId());
//                    //孙子也加入到爷爷的reply中
//                    comment.setReplyComments(commentsChild2);
//                }
//            }
        }
        return commentQueryList;
    }
}
