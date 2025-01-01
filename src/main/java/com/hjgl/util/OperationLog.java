package com.hjgl.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//在方法上标注此注解  就对该方法经行日志记录
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
//  声明注解
public @interface OperationLog {
    String description() default "";
}
