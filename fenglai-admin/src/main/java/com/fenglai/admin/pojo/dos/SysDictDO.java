package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 字典表
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
public class SysDictDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典项code
     */
    @TableField("dict_code")
    private String dictCode;

    /**
     * 字典项name
     */
    @TableField("dict_name")
    private String dictName;

    /**
     * 字典状态,0:正常,1:停用
     */
    @TableField("dict_status")
    private Integer dictStatus;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


}
