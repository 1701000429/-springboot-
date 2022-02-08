package com.cqupt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.domin.Type;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
public interface TypeMapper extends BaseMapper<Type> {
    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAllTypeAndPaper();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);
}
