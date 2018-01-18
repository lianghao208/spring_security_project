package com.imooc.security.core.validate.code.sms;

/**
 * Created by Liang Hao on 2018/1/17.
 * 短信验证码供应商接口
 */
public interface SmsCodeSender {

    /**
     * 发送短信验证码接口
     * @param mobile 手机号
     * @param code 验证码
     */
    void send(String mobile, String code);

}
