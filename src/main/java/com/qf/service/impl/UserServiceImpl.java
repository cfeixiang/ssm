package com.qf.service.impl;

import com.qf.mapper.UserMapper;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Administrator 2019/7/15 0015 15:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer checkUsername(String username) {

        //健壮性代码
        if(!StringUtils.isEmpty(username)){
            username=username.trim();
        }
        Integer count = userMapper.findCountByUsername(username);
        return count;
    }

    @Override
    public Integer register(User user) {
        //对密码加密
        String newPwd = new Md5Hash(user.getPassword(), null, 1024).toString();
        user.setPassword(newPwd);
        Integer count = userMapper.save(user);

        return count;
    }

    @Override
    public User login(String username, String password) {

        User user = userMapper.findByUsername(username);

        if(user!=null){

            String newpwd=new Md5Hash(password,null,1024).toString();

            if (user.getPassword().equals(newpwd)) {
                return user;
            }
        }

        return null;
    }
}
