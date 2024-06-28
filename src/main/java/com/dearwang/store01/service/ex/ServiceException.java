package com.dearwang.store01.service.ex;

//异常类的基类用于继承
//业务层异常基类继承异常：throws new ServiceException("业务层产生的未知异常")
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
//抛出信息同时把对象也抛出
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
