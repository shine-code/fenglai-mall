package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 组织单位表
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_org")
public class SysOrgDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组织名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * 组织类型
     */
    @TableField("org_type")
    private Integer orgType;

    /**
     * 组织状态,0:启用,1:停用
     */
    @TableField("org_status")
    private Integer orgStatus;

    /**
     * 经度
     */
    @TableField("longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @TableField("latitude")
    private Double latitude;

    /**
     * 层级路径
     */
    @TableField("hierarchy_path")
    private String hierarchyPath;

    /**
     * 组织层次
     */
    @TableField("level")
    private Integer level;


}
