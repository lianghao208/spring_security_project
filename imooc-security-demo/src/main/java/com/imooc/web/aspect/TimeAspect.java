package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/17.
 */
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("time aspect start");

        Long start = new Date().getTime();

        Object[] args = pjp.getArgs();
        for (Object arg:args){
            System.out.println("arg is " + arg);
        }

        Object object = pjp.proceed();

        System.out.println("time aspect 耗时:" + (new Date().getTime() - start));
        System.out.println("time aspect end");


        return object;
    }
}
