package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by Liang Hao on 2017/12/21.
 * 实现 UserDetailsService 接口的 loadUserByUsername 方法
 * */
@Component
public class MyUserDetailService implements UserDetailsService,SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 查询用户，用于表单登陆
     * 根据用户输入用户名，在数据库中查找密码并封装成 UserDetails 返回
     * @param username 用户输入的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名：" + username);
        //根据用户名查找用户信息
        /**
         * 从数据库中根据用户名读取加密后的密码进行验证
         */
        //根据查找到的用户判断用户是否被冻结
        return buildUser(username);        //User类实现UserDetails接口
    }

    /**
     * 第三方登录时使用
     * @param userId 根据社交网站提供的openId从数据库中查出来的userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户id：" + userId);
        //根据UserId得到User对象的封装
        return buildUser(userId);

    }

    private SocialUserDetails buildUser(String userId) {

        /**
         * 将加密后的密码写入数据库（注册时）
         */
        String password = passwordEncoder.encode("123456");//注册时的明文密码加密后写入数据库
        logger.info("数据库密码是：" + password);
        return new SocialUser(userId,  //用户名
                password,    //密码
                true,   //用户可用
                true,  //用户没有过期
                true,   //用户验证没有过期
                true,    //用户没有被冻结
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
