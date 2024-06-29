package com.dearwang.store01.mapper;


import com.dearwang.store01.entity.User;
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

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("tim123");
        user.setPassword("tim123");

        userMapper.insert(user);
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
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

}
