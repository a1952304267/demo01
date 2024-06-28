package com.dearwang.store01.mapper;


import com.dearwang.store01.entity.User;
import org.apache.ibatis.annotations.Mapper;

//用户模块接口-持久层mapper层
//描写SQL语句的抽象方法
//@Mapper
@Mapper
public interface UserMapper {
//    插入用户信息表User数据
//    返回受影响的行数
     Integer insert(User user);
//     根据用户名查找
     User findByUsername(String username);

}
