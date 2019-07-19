package com.qf.service;

import com.qf.pojo.User;

/**
 * Administrator 2019/7/15 0015 15:02
 */
public interface UserService {
    Integer checkUsername(String username);

    //注册功能
    Integer register(User user);

    User login(String username, String password);
}
