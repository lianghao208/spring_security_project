package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * Created by Liang Hao on 2018/1/20.
 * 在Demo中配置一个实现ConnectionSignUp的bean，则默认用第三方社交用户信息进行注册（数据库中没有该用户的记录则默认用社交账号注册）
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        logger.info(connection.getDisplayName());
        return connection.getDisplayName();//取得社交用户昵称
    }
}
