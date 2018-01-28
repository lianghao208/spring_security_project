package com.imooc.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Liang Hao on 2018/1/23.
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String signOutUrl;

    private ObjectMapper mapper = new ObjectMapper();

    public MyLogoutSuccessHandler(String signOutUrl){
        this.signOutUrl = signOutUrl;
    }



    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");
        //如果用户配了退出登录页面，则跳转到用户的自定义退出登录页面
        //如果用户没有配退出登录页面，则返回一个Json对象
        if (signOutUrl.equals("/defaultLogout")){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(mapper.writeValueAsString(new SimpleResponse("退出成功")));
        }else {
            httpServletResponse.sendRedirect(signOutUrl);
        }
    }
}
