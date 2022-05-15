package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 权限表
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_permission")
public class SysPermissionDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限类型
     */
    @TableField("permission_type")
    private String permissionType;

    /**
     * 资源id
     */
    @TableField("resource_id")
    private Long resourceId;


}
