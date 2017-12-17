package com.imooc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/12/17.
 * 自定义字段校验注解
 */
@Target({ElementType.METHOD, ElementType.FIELD}) //可以加在方法和属性上面
@Retention(RetentionPolicy.RUNTIME) //运行时注解
@Constraint(validatedBy = MyConstraintValidator.class) //加了注解执行什么校验逻辑
public @interface MyConstraint {

    //错误信息
    String message() default "{默认错误信息}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
