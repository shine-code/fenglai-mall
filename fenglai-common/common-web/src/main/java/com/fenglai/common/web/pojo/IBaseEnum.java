package com.fenglai.common.web.pojo;
/**
 * @description: 简单枚举类实现
 * 
 * @author: TJ
 * @date:  2022-05-19
 **/
public interface IBaseEnum<V, L> {

    V getValue();

    L getLabel();
}
