package com.imooc.security.app;

/**
 * Created by Liang Hao on 2018/1/27.
 */
public class AppSecretException extends RuntimeException{

    public AppSecretException(String msg){
        super(msg);
    }
}
