package com.dearwang.store01.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//该接口定义了三个方法    preHandle在调用所有处理请求前被自动调用
//postHandle在前端登录之后返回对象时被调用的方法
//afterCompletion所有关联资源执行完毕返回前端最后的执行方法

/*定义一个拦截器，处理前端*/
public class LoginInterceptor implements HandlerInterceptor {
    //    检测全局session对象中是否有uid数据，有则放行，无则重定向到登录页
//    request请求对象response响应对象handle处理器（url+Controller:映射）
//    true/false放行或拦截
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        HttpServletRequest获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null) {
            /*为空代表没有登录uid，则跳转到登录页*/
            response.sendRedirect("/web/login.html");
//            结束后续前端调用
            return false;

        }
//        没问题。放行
        return true;
    }
}
