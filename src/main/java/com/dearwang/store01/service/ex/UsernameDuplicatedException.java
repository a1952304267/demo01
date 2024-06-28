package com.dearwang.store01.service.ex;


//表示用户名被占用时抛出的异常
public class UsernameDuplicatedException extends ServiceException{
//    快捷建alt+insert弹出快速新建方法对话框

    public UsernameDuplicatedException() {
        super();
    }

    public UsernameDuplicatedException(String message) {
        super(message);
    }

    public UsernameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
