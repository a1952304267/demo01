package com.dearwang.store01.controller;


import com.dearwang.store01.service.ex.*;
import com.dearwang.store01.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

//控制层类基类，抛出异常
public class BaseController {
//    操作成功的状态码
    public static final int OK = 200;
//    用于统一处理抛出的异常(该方法用于返回请求处理)
//    自动将异常传递为此方法的参数列表上
//    当项目中产生了异常，被统一拦截到此方法中，方法返回值直接传给前段
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被注册");
        }else if (e instanceof UsernameNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据没找到");
        }else if (e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户名密码错误");
        }else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生了未知错误");
        }
        return result;
    }
}
