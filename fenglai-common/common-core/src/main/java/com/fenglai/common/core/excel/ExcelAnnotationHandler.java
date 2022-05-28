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

    // 提示语
    private static final String REQUIRE_TIP = "必填项不能为空";
    private static final String MIN_TIP = "内容长度小于最低限度";
    private static final String MAX_TIP = "内容长度超过限制";
    private static final String REGEXP_TIP = "内容不符合要求";
    private static final String DATE_FORMAT_TIP = "日期时间格式不符合要求";

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
            if (StrUtil.isBlank(fieldValue) || "null".equals(fieldValue.trim())) {
                return getTips(excel, REQUIRE_TIP);
            }
        }
        return null;
    };

    private static final BiFunction<Excel, String, String> min = (excel, fieldValue) -> {
        if (excel.min() > 0) {
            if (fieldValue != null && fieldValue.length() < excel.min()) {
                return getTips(excel, MIN_TIP);
            }
        }
        return null;
    };

    private static final BiFunction<Excel, String, String> max = (excel, fieldValue) -> {
        if (excel.max() > 0) {
            if (fieldValue != null && fieldValue.length() > excel.max()) {
                return getTips(excel, MAX_TIP);
            }
        }
        return null;
    };

    private static final BiFunction<Excel, String, String> regexp = (excel, fieldValue) -> {
        if (StrUtil.isNotBlank(excel.regexp())) {
            if (fieldValue != null && !Pattern.matches(excel.regexp(), fieldValue)) {
                return getTips(excel, REGEXP_TIP);
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

    /**
     * 校验提示信息前面拼上列名
     */
    private static String getTips(Excel excel, String tips) {
        String columnName = excel.columnName();
        if (StrUtil.isNotBlank(columnName)) {
            tips = "[" + columnName + "]" + tips;
        }
        return tips;
    }

    static {
        checkFunction.add(require);
        checkFunction.add(min);
        checkFunction.add(max);
        checkFunction.add(regexp);
        checkFunction.add(dateFormat);
    }
}
