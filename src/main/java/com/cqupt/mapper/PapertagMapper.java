package com.cqupt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.domin.Papertag;
import com.cqupt.domin.Tag;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-09
 */
public interface PapertagMapper extends BaseMapper<Papertag> {
    int  savePapertag(Papertag papertag);

}
