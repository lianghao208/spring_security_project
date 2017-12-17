package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Administrator on 2017/12/17.
 * 自定义validator校验器注解的逻辑类
 * 注解名：MyConstraint
 * 作用于什么类型的字段：Object
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {
    /**
     * 初始化注解
     * @param myConstraint
     */
    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }

    /**
     * 校验的属性为Object o，在这个方法里写校验逻辑
     * @param o
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(o);
        return true;// true：校验成功  false：校验失败
    }
}
