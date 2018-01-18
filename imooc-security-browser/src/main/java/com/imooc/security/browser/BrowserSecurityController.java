package com.imooc.security.browser;

import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/21.
 */
@RestController
public class BrowserSecurityController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 需要身份验证时跳转到这里(做一个请求的逻辑判断，根据不同类型请求返回不同内容)
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus( code = HttpStatus.UNAUTHORIZED)   //返回401未授权状态码
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException{
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")){ //查看请求的url是不是以html结尾
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage()); //如果请求是html请求，直接重定向到登录界面不返回401状态码
            }
        }
        return new SimpleResponse("访问的服务需要身份验证，请引导用户到登录页");//返回401状态码和Json错误信息
    }
}