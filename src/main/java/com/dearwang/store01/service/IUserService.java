package com.dearwang.store01.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dearwang.store01.entity.User;

//用户模块的业务层接口
//注释掉的是mybatis-plus的继承类
//public interface IUserService extends IService<User> {
//    boolean save(User entity);
//    boolean result = IUserService.save(user);
public interface IUserService {

    //    用户注册方法 user用户的数据对象
    void reg(User user);

    //    登录时的数据对象
    User login(String username, String password);


    //更新时的数据对象
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);
}
