package com.dearwang.store01.service;


import com.dearwang.store01.entity.User;

//用户模块的业务层接口
public interface IUserService {

//    用户注册方法 user用户的数据对象
    void reg(User user);
//    登录时的数据对象
    User login(String username,String password);

}
