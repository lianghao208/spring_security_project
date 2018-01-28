package com.imooc.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.support.SimpleResponse;
import com.imooc.security.core.properties.LoginResponseType;
import com.imooc.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/21.
 * 登录失败的处理类，打印登录失败的authentication的Json信息到前端（过滤器的作用，可自行修改Json格式并打印）
 * TODO 打印前端发送AJAX请求所需要的Json信息（登录失败）
 */
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     *
     * @param request
     * @param response
     * @param exception 包含用户登录的异常信息(错误消息、java错误堆栈等)
     * @throws IOException
     * @throws ServletException
     * TODO 打印前端发送AJAX请求所需要的Json信息（登录失败）
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())){//是Json请求则进行Json数据的返回
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());//返回500状态码
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));//将登陆失败的authentication信息打印到前台
        }else {//不是需要返回Json的请求类型则让父类进行跳转（非AJAX异步请求）
            super.onAuthenticationFailure(request,response,exception);
        }

    }
}
