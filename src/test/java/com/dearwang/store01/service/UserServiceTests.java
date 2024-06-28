package com.dearwang.store01.service;


import com.dearwang.store01.entity.User;
import com.dearwang.store01.mapper.UserMapper;
import com.dearwang.store01.service.ex.ServiceException;
import com.dearwang.store01.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//标记为测试类
@SpringBootTest
//启动单元测试类，需要传参必须是SpringRunner实例类型class
@RunWith(SpringRunner.class)
public class UserServiceTests {
    //    idea有检测功能，接口不可以直接创建bean
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("demo1234");
            user.setPassword("tim123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
//            获取类对象名称
            System.out.println(e.getClass());
//            获取异常具体描述信息
            System.out.println(e.getMessage());

        }
    }

    @Test
    public void login(){
       User user= userService.login("admin","123456");
        System.out.println(user);
    }

}
