package com.dearwang.store01.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dearwang.store01.entity.User;
import org.apache.ibatis.annotations.Param;

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

    //    查询用户数据
    User getByUid(Integer uid);

    //    更新用户数据时的数据对象
    void changeInfo(Integer uid, String username, User user);


    //    用户上传头像时候的数据对象
    void changeAvatar(Integer uid,
                      String avatar,
                      String username);

}

