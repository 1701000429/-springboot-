package com.cqupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.domin.Type;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
public interface TypeService extends IService<Type> {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type>getAllTypeAndBlog();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);
}
