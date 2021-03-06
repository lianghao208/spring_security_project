package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/21.
 */
@ConfigurationProperties(prefix = "my.security")  //读取用户设置的my.security为前缀的配置
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * 社交网站配置
     */
    private SocialProperties social = new SocialProperties();

    /**
     * 配置OAuth2
     */
    private OAuth2Properties oAuth2Properties = new OAuth2Properties();

    public OAuth2Properties getoAuth2Properties() {
        return oAuth2Properties;
    }

    public void setoAuth2Properties(OAuth2Properties oAuth2Properties) {
        this.oAuth2Properties = oAuth2Properties;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
