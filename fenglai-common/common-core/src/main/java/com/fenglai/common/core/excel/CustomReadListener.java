package com.fenglai.common.core.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.fenglai.common.core.utils.JsonUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @description: 自定义读取数据监听
 * 
 * @author: TJ
 * @date:  2022-05-26
 **/
public class CustomReadListener<T> implements ReadListener<T> {

    public int BATCH_COUNT = 100;
    /**
     * Temporary storage of data
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 校验失败的数据
     */
    private final List<ExcelFailResult> failResults = new ArrayList<>();
    /**
     * 校验字段集合
     */
    private List<Field> validFields;
    /**
     * 消费者函数, 用于处理读取到的每一行数据
     */
    private final Consumer<List<T>> consumer;

    public CustomReadListener(Consumer<List<T>> consumer) {
        this.consumer = consumer;
    }

    public CustomReadListener(Consumer<List<T>> consumer, int batchCount) {
        this.consumer = consumer;
        this.BATCH_COUNT = batchCount;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {

        if (CollUtil.isNotEmpty(validFields)) {

            String json = JsonUtil.toJson(data);
            for (Field field : validFields) {
                String fieldName = field.getName();
                String fieldValue = JsonUtil.getNodeValue(json, fieldName);

                // 根据Excel注解属性作对应校验
                String checkMsg = ExcelAnnotationHandler.checkValidity(field.getAnnotation(Excel.class), fieldValue);
                if (StrUtil.isNotBlank(checkMsg)) {
                    failResults.add(new ExcelFailResult()
                            .setRowNUm(context.readRowHolder().getRowIndex())
                            .setFailMessage(checkMsg)
                            .setRowData(data));
                    return;
                }
            }
        }
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            consumer.accept(cachedDataList);
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (CollUtil.isNotEmpty(cachedDataList)) {
            consumer.accept(cachedDataList);
        }
    }

    public void setValidFields(List<Field> validFields) {
        this.validFields = validFields;
    }

    public List<ExcelFailResult> getFailResults() {
        return failResults;
    }
}
