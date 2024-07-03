package com.dearwang.store01.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dearwang.store01.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

//用户模块接口-持久层
//描写SQL语句的抽象方法
//@Mapper
//继承mybatis-plus
public interface UserMapper extends BaseMapper<User> {
    //    插入用户信息表User数据
//    返回受影响的行数
//    User user = new User();
    int insert(User user);
//    int update(User password);
    /**
     * 根据uid查找修改password
     * @param uid 获取用户的id
     * @param password 用户输入的新密码
     * @param modifiedUser  修改的执行者，谁修改
     * @param modifiedTime  修改数据的时间
     * @return 返回受影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password,
                                String modifiedUser,
                                Date modifiedTime);
    User findByUid(Integer uid);/*查找UID数据对象*/
    //     根据用户名查找
    User findByUsername(String username);

    /**
     * 更新用户个人信息
     * @param user 根据uid更新
     * @return 返回受影响的行数
     */
    Integer updateInfoByUid(User user);
}
