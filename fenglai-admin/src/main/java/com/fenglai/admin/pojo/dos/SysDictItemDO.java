package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 字典数据表
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict_item")
public class SysDictItemDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典数据code
     */
    @TableField("item_code")
    private String itemCode;

    /**
     * 字典数据name
     */
    @TableField("item_name")
    private String itemName;

    /**
     * 字典项code
     */
    @TableField("dict_code")
    private String dictCode;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 状态,0:正常,1:停用
     */
    @TableField("item_status")
    private Integer itemStatus;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


}
