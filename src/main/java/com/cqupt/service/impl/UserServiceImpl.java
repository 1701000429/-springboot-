package com.cqupt.service.impl;

import com.cqupt.domin.User;
import com.cqupt.mapper.UserMapper;
import com.cqupt.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User doLogin(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
