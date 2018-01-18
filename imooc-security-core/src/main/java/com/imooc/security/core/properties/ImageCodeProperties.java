package com.imooc.security.core.properties;

import com.imooc.security.core.validate.code.ImageCode;

/**
 * Created by Administrator on 2017/12/22.
 * 图形验证码配置
 */
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 67;

    private int height = 23;

    public ImageCodeProperties(){
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
