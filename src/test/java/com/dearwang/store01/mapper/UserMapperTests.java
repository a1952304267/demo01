package com.dearwang.store01.mapper;


import com.dearwang.store01.entity.User;
import com.dearwang.store01.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//标记为测试类
@SpringBootTest
//启动单元测试类，需要传参必须是SpringRunner实例类型class
@RunWith(SpringRunner.class)
public class UserMapperTests {
    //    idea有检测功能，接口不可以直接创建bean
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService iUserService;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("text");
        user.setPassword("123");

        iUserService.reg(user);
//        此处调用应该为业务层的接口，而不是持久层接口
//        Integer rows = userMapper.insert(user);
        System.out.println(user);
//        必须为@Test修饰
//    返回值必须是void
//    方法参数列表不指定任何
//        修饰符必须是public

    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
}
    /*单元测试不可以有参数列表*/
    @Test
    public void updatePasswordByUid(){

//        iUserService.changePassword(51);
        userMapper.updatePasswordByUid(
                46,
                "321",
                "管理员",
                new Date());
    }
    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(46));
    }

    @Test
    public void changePassword(){
        iUserService.changePassword(51,
                "管理员",
                "123",
                "1234");
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(51);
        user.setPhone("13971561561");
        user.setEmail("text@qq.com");
        user.setGender(1);
        user.setModifiedTime(new Date());
        userMapper.updateInfoByUid(user);
    }
}
