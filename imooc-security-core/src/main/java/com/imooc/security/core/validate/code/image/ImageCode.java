/**
 * 
 */
package com.imooc.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.imooc.security.core.validate.code.ValidateCode;


/**
 * Created by Liang Hao on 2018/1/17.
 */
public class ImageCode extends ValidateCode implements Serializable{

	private static final long serialVersionUID = -7466318910874157089L;

	private BufferedImage image;
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
