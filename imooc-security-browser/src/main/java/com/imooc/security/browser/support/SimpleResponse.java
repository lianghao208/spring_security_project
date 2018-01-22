package com.imooc.security.browser.support;

/**
 * Created by Liang Hao on 2017/12/21.
 * 服务器响应的内容对象
 */
public class SimpleResponse {

    public SimpleResponse(Object content){
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
