package com.qf.mapper;

import com.qf.AcTest;
import com.qf.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Administrator 2019/7/15 0015 19:34
 */
public class UserMapperTest extends AcTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void findCountByUsername() {
        Integer count = userMapper.findCountByUsername("admin1");
        System.out.println(count);
    }
    @Test
    @Transactional
    public void save(){
        User user=new User();
        user.setUsername("sss");
        user.setPassword("sss");
        user.setPhone("11111");
        Integer count = userMapper.save(user);
       //断言
        assertEquals(new Integer(1),count);
    }
    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("车威远");
        System.out.println(user);
    }
}