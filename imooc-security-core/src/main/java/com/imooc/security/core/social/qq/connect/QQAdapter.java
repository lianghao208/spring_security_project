package com.imooc.security.core.social.qq.connect;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * Created by Liang Hao on 2018/1/19.
 */
public class QQAdapter implements ApiAdapter<QQ>{

    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInfo = null;

        userInfo = qq.getUserInfo();

        //从QQ用户信息中拿出QQ昵称
        connectionValues.setDisplayName(userInfo.getNickname());
        //从QQ用户信息中拿出QQ用户40*40像素的头像
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        //从QQ用户信息中拿出QQ用户的个人主页，没有则为空
        connectionValues.setProfileUrl(null);
        //从QQ用户信息中拿出QQ服务提供商的openId
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
