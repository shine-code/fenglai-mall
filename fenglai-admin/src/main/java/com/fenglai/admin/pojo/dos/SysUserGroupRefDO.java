package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 用户-用户组关联
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_group_ref")
public class SysUserGroupRefDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组id
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;


}
