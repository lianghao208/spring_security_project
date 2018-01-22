package com.imooc.security.core.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绑定信息
 * Created by Liang Hao on 2018/1/22.
 */
public class MyConnectView extends AbstractView {


    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {


        httpServletResponse.setContentType("text/html;charset=UTF-8");
        if (model.get("connection") == null){
            httpServletResponse.getWriter().write("<h3>绑定成功</h3>");
        } else {
            httpServletResponse.getWriter().write("<h3>解绑成功</h3>");
        }

    }
}
