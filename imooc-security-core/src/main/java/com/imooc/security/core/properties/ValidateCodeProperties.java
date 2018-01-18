package com.imooc.security.core.properties;

import com.imooc.security.core.validate.code.sms.SmsCodeSender;

/**
 * Created by Administrator on 2017/12/22.\
 * 所有验证码配置
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();


    public ImageCodeProperties getImage() {
        return image;
    }

    public SmsCodeProperties getSms(){return sms;}

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public void setSms(SmsCodeProperties sms){
        this.sms = sms;
    }
}
