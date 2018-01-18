package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by Administrator on 2017/12/22.
 * 验证码生成接口
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
