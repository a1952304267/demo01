package com.dearwang.store01.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dearwang.store01.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     *
     * @param uid          获取用户的id
     * @param password     用户输入的新密码
     * @param modifiedUser 修改的执行者，谁修改
     * @param modifiedTime 修改数据的时间
     * @return 返回受影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password,
                                String modifiedUser,
                                Date modifiedTime);

    /*查找UID数据对象*/
    User findByUid(Integer uid);

    //     根据用户名查找
    User findByUsername(String username);

    /**
     * 更新用户个人信息
     *
     * @param user 根据uid更新
     * @return 返回受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户的uid来修改用户头像
     *
     * @param uid
     * @param avatar       用户的头像字段
     * @param modifiedUser
     * @param modifiedTime
     * @return 返回受影响的行数
     * //@Param 表示指定sql语句中字段名与当前方法定义的对象对应绑定,相同可以省略，不同则强行匹配
     * 这里省略其他三个字段的@Param注解
     * !与SQL语句中的#{}占位符中的字段名对应
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              String avatar,
                              String modifiedUser,
                              Date modifiedTime);
}
