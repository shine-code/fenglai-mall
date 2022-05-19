package com.fenglai.common.web.utils;

import com.fenglai.common.web.pojo.IBaseEnum;

/**
 * @description: 枚举类基本方法
 * 
 * @author: TJ
 * @date:  2022-05-19
 **/
@SuppressWarnings("all")
public class EnumUtil {

    /**
     * 判断枚举中是否存在目标值
     * @param enums 目标枚举类 values()
     * @param value 目标值
     * @return 是否存在-boolean
     */
    public static boolean isExist(IBaseEnum[] enums, Object value) {
        if (value == null) {
            return false;
        }
        for (IBaseEnum e : enums) {
            if (value.equals(e.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据枚举类中value获取label
     * @param enums 目标枚举类
     * @param value 目标值
     * @return label
     */
    public static Object getLabelByValue(IBaseEnum[] enums, Object value) {
        if (value == null) {
            return "";
        }
        for (IBaseEnum e : enums) {
            if (value.equals(e.getValue())) {
                return e.getLabel();
            }
        }
        return "";
    }
}
