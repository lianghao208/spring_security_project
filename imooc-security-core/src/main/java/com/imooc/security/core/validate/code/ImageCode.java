package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/12/22.
 * 验证码类
 */
public class ImageCode extends ValidateCode {

    private BufferedImage image; //验证码图片

    /**
     *
     * @param image
     * @param code
     * @param expireInt  过期时间段
     */
    public ImageCode(BufferedImage image, String code, int expireInt){
        super(code,expireInt);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
