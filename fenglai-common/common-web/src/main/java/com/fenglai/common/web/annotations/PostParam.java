package com.fenglai.common.web.annotations;

import java.lang.annotation.*;

/**
 * @description: 用于从post请求的body中绑定参数
 * 
 * @author: TJ
 * @date:  2022-05-19
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PostParam {

    /**
     * 要绑定的参数名
     */
    String value() default "";

    /**
     * not use
     */
    boolean required() default false;
    /**
     * not use
     */
    String defaultValue() default "";
}
