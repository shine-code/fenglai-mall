package com.fenglai.common.core.utils;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.excel.EasyExcel;
import com.fenglai.common.core.excel.Excel;
import com.fenglai.common.core.excel.CustomReadListener;
import com.fenglai.common.core.excel.ExcelFailResult;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @description: excel操作
 *
 * @author: TJ
 * @date:  2022-05-24
 **/
@Slf4j(topic = "common-core-ExcelUtil")
public class ExcelUtil {

    public static <T> List<ExcelFailResult> importData(InputStream inputStream, Class<?> readDTO, Consumer<List<T>> consumer) {

        CustomReadListener<T> listener = new CustomReadListener<>(consumer);
        listener.setValidFields(getValidFields(readDTO));

        EasyExcel.read(inputStream, readDTO, listener).sheet().headRowNumber(2).doRead();
        return listener.getFailResults();
    }

    private static List<Field> getValidFields(Class<?> readDTO) {
        Field[] fields = ReflectUtil.getFields(readDTO);
        // 取包含Excel注解的字段作相应校验
        return Arrays.stream(fields)
                .filter(field -> field.getAnnotation(Excel.class) != null)
                .collect(Collectors.toList());
    }
}
