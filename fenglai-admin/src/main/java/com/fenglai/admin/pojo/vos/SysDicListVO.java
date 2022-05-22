package com.fenglai.admin.pojo.vos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: 字典数据VO
 * 
 * @author: TJ
 * @date:  2022-05-22
 **/
@Data
@Accessors(chain = true)
public class SysDicListVO {

    /**
     * 字典id
     */
    private Long dictId;
    /**
     * 字典code
     */
    private String dictCode;
    /**
     * 字典name
     */
    private String dictName;
    /**
     * 字典备注
     */
    private String remark;
    /**
     * 序号
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    private List<DictItem> dictItems;

    @Data
    @Accessors(chain = true)
    public static class DictItem {
        /**
         * 字典数据项id
         */
        private Long itemId;
        /**
         * 数据项code
         */
        private String itemCode;
        /**
         * 数据项name
         */
        private String itemName;
        /**
         * 数据项备注
         */
        private String remark;
        /**
         * 序号
         */
        private Integer sort;
        /**
         * 状态
         */
        private Integer status;
    }
}
