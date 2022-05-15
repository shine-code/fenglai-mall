package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 角色权限表
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_permission")
public class SysRolePermissionDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;


}
