package com.dearwang.store01.controller;


import com.dearwang.store01.entity.User;
import com.dearwang.store01.service.IUserService;
import com.dearwang.store01.service.ex.InsertException;
import com.dearwang.store01.service.ex.UsernameDuplicatedException;
import com.dearwang.store01.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller与@requestbody混合为下面这个注解
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    //    @RequestMapping("reg")
//    public JsonResult<Void> reg(User user) {
////        创建响应结果对象
//        JsonResult<Void> result = new JsonResult<>();
//        try {
//            userService.reg(user);
//            result.setState(200);
//            result.setMessage("注册成功");
//        } catch (UsernameDuplicatedException e) {
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        } catch (InsertException e) {
//            result.setState(5000);
//            result.setMessage("注册时产生了未知错误");
//        }
//        return result;
//
//    }

//    接收数据方式：请求处理方法设置为pojo类型来接受前端的数据，springboot会将前端的url地址将pojo类中的属性进行比较
//    2.请求数据类型参数列表设置为非pojo类型，如string类型，springboot会直接将请求的参数名和方法的参数名进行比较
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
//        创建响应结果对象
        userService.reg(user);
        return new JsonResult<Void>(OK);

    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password) {
        User data = userService.login(username, password);
        return new JsonResult<User>(OK,data);
    }
}