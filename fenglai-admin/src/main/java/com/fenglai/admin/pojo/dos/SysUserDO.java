package com.fenglai.admin.pojo.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fenglai.common.web.pojo.BaseEntity;

/**
 * @description: 用户表
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编码
     */
    @TableField("user_code")
    private String userCode;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 组织id
     */
    @TableField("org_id")
    private Long orgId;

    /**
     * 性别,0:女,1:男
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 联系方式
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户状态
     */
    @TableField("user_status")
    private Integer userStatus;


}
