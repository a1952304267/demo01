package com.dearwang.store01.service.impl;


import com.dearwang.store01.entity.User;
import com.dearwang.store01.mapper.UserMapper;
import com.dearwang.store01.service.IUserService;
import com.dearwang.store01.service.ex.InsertException;
import com.dearwang.store01.service.ex.PasswordNotMatchException;
import com.dearwang.store01.service.ex.UpdateException;
import com.dearwang.store01.service.ex.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

//用户模块的实现类
@Service//@Service将当前对象交给spring来管理
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    //    方法重写,注册方法
    @Override
    public void reg(User user) {
//        通过user参数来获取传递过来的username
        String username = user.getUsername();

//      调用findByUsername（username）判断用户是否被注册
        User result = userMapper.findByUsername(username);
//        判断结果接result是否不为null，则抛出异常
        if (result != null) {
//            抛出异常
            throw new UnsupportedOperationException("用户名被占用");
        }

//        密码加密处理的实现，适用md5算法
//        串+password+串——md5
//        串--盐值,随机字符串
        String oldPassword = user.getPassword();
//        获取盐值（随机生成盐值）
        String salt = UUID.randomUUID().toString().toUpperCase();
//          记录盐值
        user.setSalt(salt);
//        将密码和盐值作为一个整体进行操作!!!!提升数据安全性
        String md5Password = getMD5Password(oldPassword, salt);
//加密后代码重新补全设置到user
        user.setPassword(md5Password);

//        此类应该要补全数据！！！！！
//        补全isdelete字段为0不删除
        user.setIsDelete(0);
//        补全剩余的四个日志字段
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        //首次创建的时间
        user.setModifiedTime(date);

//        执行注册业务功能的实现row==1时显示插入成功
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在注册用户过程中产生了未知的异常");
        }


    }

    //    方法重写,登录方法
    @Override
    public User login(String username, String password) {
//        根据用户名称来查询用户数据是否存在，若不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
//        检测账户密码是否存匹配
//        获取数据库中的代码
        String oldPassword = result.getPassword();

//        与用户传递过来的密码进行比较，获取盐值与md5规则进行加密
        String salt = result.getSalt();
        String newMd5Password = getMD5Password(password, salt);
//        比较equals相等equalsIgnoreCase忽略大小写startsWith和endsWith检查对应开头与对应结尾
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("密码输入错误");
        }
//        判断是否被删除is_delete字段
        if (result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户不存在或已被注销");
        }
//        数据压缩,同时返回user下的所有数据
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
//        user.setPassword(result.getPassword());
//      返回数据
        return user;
    }


    //    更新数据操作
    /**
     *
     * @param uid 根据用户id查找是否存在,并进行对应id的用户的密码更改
     * @param username 更新时的用户名——谁处理的这个更新密码时的操作
     * @param oldPassword 更新时的老密码
     * @param newPassword 更新时的新密码
     */
    @Override
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword) {
        User result = userMapper.findByUid(uid);
//        比较原密码与数据库中密码
        if (result == null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户不存在");
        }
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
//       保存新密码，并进行加密
        String newMd5Password = getMD5Password(newPassword,result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid,
                                                      newMd5Password,
                                                      username,
                                                      new Date());
        if (rows!=1){
            throw new UpdateException("数据更新时产生了未知错误");
        }
    }

    //    定义一个md5算法加密方法
    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
//        返回加密后密码
    }

}
