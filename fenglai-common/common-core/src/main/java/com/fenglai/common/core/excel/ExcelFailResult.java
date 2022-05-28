package com.fenglai.common.core.excel;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-05-25
 **/
@Data
@Accessors(chain = true)
public class ExcelFailResult {

    /**
     * excel中行号
     */
    private int rowNUm;
    /**
     * 失败原因
     */
    private String failMessage;
    /**
     * 行数据
     */
    private Object rowData;
}
