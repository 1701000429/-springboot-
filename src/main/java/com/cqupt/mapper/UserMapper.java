package com.cqupt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.domin.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
public interface UserMapper extends BaseMapper<User> {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
