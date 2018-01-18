package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Liang Hao on 2017/12/21.
 * 实现 UserDetailsService 接口的 loadUserByUsername 方法
 * */
@Component
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 查询用户
     * 用户输入用户名，在数据库中查找密码并封装成 UserDetails 返回
     * @param username 用户输入的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名：" + username);
        //根据用户名查找用户信息

        /**
         * 将加密后的密码写入数据库（注册时）
         */
        String password = passwordEncoder.encode("123456");//注册时的明文密码加密后写入数据库
        logger.info("数据库密码是：" + password);

        /**
         * 从数据库中根据用户名读取加密后的密码进行验证
         */
        //根据查找到的用户判断用户是否被冻结
        return new User(username,  //用户名
                password,    //密码
                true,   //用户可用
                true,  //用户没有过期
                true,   //用户验证没有过期
                true,    //用户没有被冻结
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //User类实现UserDetails接口
        //return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));//User对象为Spring自带的Spring对象
    }
}
