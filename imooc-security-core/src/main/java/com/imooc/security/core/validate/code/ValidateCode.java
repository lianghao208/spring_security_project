package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/12/22.
 * 验证码类
 */
public class ValidateCode {

    private String code; //随机数验证码

    private LocalDateTime expireTime; //验证码失效时间点

    /**
     *
     * @param code
     * @param expireInt  过期时间段
     */
    public ValidateCode( String code, int expireInt){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireInt);//生成过期时间点
    }

    public ValidateCode( String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
