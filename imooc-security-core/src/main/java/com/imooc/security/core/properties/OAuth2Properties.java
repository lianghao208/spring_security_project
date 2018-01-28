package com.imooc.security.core.properties;

/**
 * Created by Liang Hao on 2018/1/27.
 */
public class OAuth2Properties {

    private OAuth2ClientProperties[] clients = {};

    //jwt密钥
    private String jwtSigningKey = "my";

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }
}
