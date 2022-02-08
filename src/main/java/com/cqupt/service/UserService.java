package com.cqupt.service;

import com.cqupt.domin.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
public interface UserService extends IService<User> {

    //处理登录
    User doLogin(String username, String password);
}
