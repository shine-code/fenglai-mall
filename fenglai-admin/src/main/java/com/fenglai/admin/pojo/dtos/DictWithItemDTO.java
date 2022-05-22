package com.fenglai.admin.pojo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: 字典及数据项
 * 
 * @author: TJ
 * @date:  2022-05-22
 **/
@Data
@Accessors(chain = true)
public class DictWithItemDTO {

    private Long dictId;
    private String dictCode;
    private String dictName;
    private String dictRemark;
    private Integer dictSort;
    private Integer dictStatus;

    private Long itemId;
    private String itemCode;
    private String itemName;
    private String itemRemark;
    private Integer itemSort;
    private Integer itemStatus;
}
