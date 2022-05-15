package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 用户组
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_group")
public class SysUserGroupDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组名
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 用户组状态,0:启用,1:停用,2:删除
     */
    @TableField("group_status")
    private Integer groupStatus;


}
