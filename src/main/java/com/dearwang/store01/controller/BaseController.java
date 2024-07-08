package com.dearwang.store01.controller;


import com.dearwang.store01.service.ex.*;
import com.dearwang.store01.service.ex.address.AddressCountLimitException;
import com.dearwang.store01.service.ex.avatar.*;
import com.dearwang.store01.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

//控制层类基类，抛出异常
public class BaseController {
    //    操作成功的状态码
    public static final int OK = 200;

    /*
    用于统一处理抛出的异常(该方法用于返回请求处理)
    自动将异常传递为此方法的参数列表上
    当项目中产生了异常，被统一拦截到此方法中，方法返回值直接传给前段
    使用@ExceptionHandler绑定异常处理类的时候，可以使用数组对象，使用“，”分隔
    */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handlerException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已经被注册");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4001);
            result.setMessage("收货地址数量超过上线");
        } else if (e instanceof UsernameNotFoundException) {
            result.setState(5001);
            result.setMessage("用户数据没找到");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户名密码错误");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("用户名已被注册或出现了未知异常");
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("更新密码时产生了未知错误");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000); //表示上传文件为空错误
            result.setMessage("上传文件为空");
        } else if (e instanceof FileSizeException) {
            result.setState(6001); //表示上传文件超过限制错误
            result.setMessage("上传文件超过限制");
        } else if (e instanceof FileStateException) {
            result.setState(6002); //表示上传文件状态异常错误
            result.setMessage("上传文件状态异常");
        } else if (e instanceof FileTypeException) {
            result.setState(6003); //表示上传文件类型不符错误
            result.setMessage("上传文件类型不符");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);//表示上传文件IO读取错误
            result.setMessage("上传文件IO读取错误");
        }
        return result;
    }

    //    可以使用protected定义仅当前包内对象能够使用
    //    定义session对象获取session对象中的id
    public final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    //    定义session对象获取session对象中的当前登录的username
    public final String getUsernameFromSession(HttpSession session) {
        return String.valueOf(session.getAttribute("username").toString());
    }
}
