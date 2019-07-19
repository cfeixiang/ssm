package com.qf.service;

import com.qf.AcTest;
import com.qf.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Administrator 2019/7/15 0015 19:46
 */
public class UserServiceTest extends AcTest {

    @Autowired
    private UserService userService;
    @Test
    public void checkUsername() {
        Integer count = userService.checkUsername("aaa");
        System.out.println(count);
    }
    @Test
    //@Transactional
    public void register(){
        User user=new User();
        user.setUsername("xxxx");
        user.setPassword("xxxx");
        user.setPhone("1111111");
        Integer count = userService.register(user);
        //assertEquals(new Integer(1),count);
        System.out.println(count);
    }
    @Test
    public void login(){

        User user = userService.login("车威远", "123");
        System.out.println(user);
    }
}