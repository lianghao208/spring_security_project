package com.imooc.security.core.validate.code.sms;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by Liang Hao on 2018/1/17.
 * 默认短信验证码实现
 */
public class DefaultSmsCodeSender implements SmsCodeSender{

    /**
     * 默认接口实现
     * @param mobile 手机号
     * @param code 验证码
     */
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机号：" + mobile + " 发送短信验证码：" + code);
    }
}
