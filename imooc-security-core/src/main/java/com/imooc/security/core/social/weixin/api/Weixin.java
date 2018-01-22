/**
 * 
 */
package com.imooc.security.core.social.weixin.api;

/**
 * 微信API调用接口
 * 
 * Created by Liang Hao
 *
 */
public interface Weixin {

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
	WeixinUserInfo getUserInfo(String openId);
	
}
