package com.imooc.security.browser.authentication;


import com.imooc.security.core.properties.LoginResponseType;
import com.imooc.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/21.
 * 登录成功的处理类，打印登录成功的authentication的Json信息到前端（过滤器的作用，可自行修改Json格式并打印）
 * TODO 打印前端发送AJAX请求所需要的Json信息（登录成功）
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     *
     * @param request
     * @param response
     * @param authentication 包含用户的登录信息(授权、是否通过验证、用户名、sessionId等)
     * @throws IOException
     * @throws ServletException
     * TODO 打印前端发送AJAX请求所需要的Json信息（登录成功）
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())){//是Json请求则进行Json数据的返回
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));//将登陆成功的authentication信息打印到前台
        }else {//不是需要返回Json的请求类型则让父类进行跳转（非AJAX异步请求）
            super.onAuthenticationSuccess(request,response,authentication);
        }

    }
}
