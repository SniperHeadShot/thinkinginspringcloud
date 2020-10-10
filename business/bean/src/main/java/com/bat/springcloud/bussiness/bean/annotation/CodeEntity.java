package com.bat.springcloud.bussiness.bean.annotation;

import java.lang.annotation.*;

/**
 * 响应结构体注解
 *
 * @author ZhengYu
 * @version 1.0 2019/10/30 11:53
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CodeEntity {

    boolean success();

    int errCode() default 0;

    String msg();
}
