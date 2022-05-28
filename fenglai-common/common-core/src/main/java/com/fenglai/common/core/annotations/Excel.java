package com.fenglai.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: excel操作对象属性设置
 * 
 * @author: TJ
 * @date:  2022-05-25
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    // ******************    通用属性       *******************************
    /**
     * 列名
     */
    String columnName() default "";


    // ******************    导入使用       *******************************
    /**
     * 字段是否必填, 空字符串等同于null
     */
    boolean required() default false;
    /**
     * 字段最小长度
     */
    int min() default 0;
    /**
     * 字段最大长度, 小于0的情况代表不限制
     */
    int max() default -1;
    /**
     * 正则
     */
    String regexp() default "";
    /**
     * 日期时间格式, 若未指定格式, 则根据内置的格式尽可能解析
     */
    String dateFormat() default "";

}
