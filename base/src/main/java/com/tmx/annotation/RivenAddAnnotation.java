package com.tmx.annotation;

import java.lang.annotation.*;

/**
 * 给一个方法新增注释
 * Created By Riven on 2020-9-18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RivenAddAnnotation {

    String id() default "";

    String account() default "admin";

    String password() default "123456";

    String name() default "";

    String age() default "";
}
