package com.imooc.web.controller;

import com.imooc.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/17.
 * Controller增强类，包含ExceptionHandler、InitBinder、ModelAttribute
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 异常处理器
     * @param ex
     * @return
     */
    @ExceptionHandler(UserNotExistException.class) //当抛出这个异常时才执行这个方法
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //返回500错误
    public Map<String, Object> handlerUserNotExistException(UserNotExistException ex){
        Map<String, Object> result = new HashMap<>();
        result.put("id",ex.getId());
        result.put("message",ex.getMessage());
        return result;
    }
}
