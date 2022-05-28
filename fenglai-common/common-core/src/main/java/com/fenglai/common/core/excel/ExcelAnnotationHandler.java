package com.fenglai.common.core.excel;

import cn.hutool.core.util.StrUtil;
import com.fenglai.common.core.annotations.Excel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

/**
 * @description: Excel注解属性校验
 * 
 * @author: TJ
 * @date:  2022-05-26
 **/
public class ExcelAnnotationHandler {

    // 属性校验函数集合
    private static final List<BiFunction<Excel, String, String>> checkFunction = new ArrayList<>();

    /**
     * 执行校验函数
     * @param excel 注解对象
     * @param fieldValue 字段值
     * @return 校验信息
     */
    public static String checkValidity(Excel excel, String fieldValue) {
        for (BiFunction<Excel, String, String> biFunction : checkFunction) {
            String checkMsg = biFunction.apply(excel, fieldValue);
            if (StrUtil.isNotBlank(checkMsg)) {
                return checkMsg;
            }
        }
        return null;
    }

    private static final BiFunction<Excel, String, String> require = (excel, fieldValue) -> {
        if (excel.required()) {
            if (StrUtil.isBlank(fieldValue)) {
                String msg = "该列内容必填项不能为空";
                String columnName = excel.columnName();
                if (StrUtil.isNotBlank(columnName)) {
                    msg = "[" + columnName + "]" + msg;
                }
                return msg;
            }
        }
        return null;
    };

    private static final BiFunction<Excel, String, String> min = (excel, fieldValue) -> {
        if (excel.min() > 0) {
            if (fieldValue != null && fieldValue.length() < excel.min()) {
                String msg = "该列内容长度小于最低限度";
                String columnName = excel.columnName();
                if (StrUtil.isNotBlank(columnName)) {
                    msg = "[" + columnName + "]" + msg;
                }
                return msg;
            }
        }
        return null;
    };

    private static final BiFunction<Excel, String, String> max = (excel, fieldValue) -> {
        if (excel.max() > 0) {
            if (fieldValue != null && fieldValue.length() > excel.max()) {
                String msg = "该列内容长度超过限制";
                String columnName = excel.columnName();
                if (StrUtil.isNotBlank(columnName)) {
                    msg = "[" + columnName + "]" + msg;
                }
                return msg;
            }
        }
        return null;
    };

    private static final BiFunction<Excel, String, String> regexp = (excel, fieldValue) -> {
        if (StrUtil.isNotBlank(excel.regexp())) {
            if (fieldValue != null && !Pattern.matches(excel.regexp(), fieldValue)) {
                String msg = "该列内容不符合要求";
                String columnName = excel.columnName();
                if (StrUtil.isNotBlank(columnName)) {
                    msg = "[" + columnName + "]" + msg;
                }
                return msg;
            }
        }
        return null;
    };

    private static final BiFunction<Excel, String, String> dateFormat = (excel, fieldValue) -> {
        if (StrUtil.isNotBlank(excel.dateFormat())) {
            // TODO
            return null;
        }
        return null;
    };

    static {
        checkFunction.add(require);
        checkFunction.add(min);
        checkFunction.add(max);
        checkFunction.add(regexp);
        checkFunction.add(dateFormat);
    }
}
