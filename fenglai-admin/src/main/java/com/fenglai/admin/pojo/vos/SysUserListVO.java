package com.fenglai.admin.pojo.vos;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-05-19
 **/
@Data
@Accessors(chain = true)
public class SysUserListVO {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 组织单位id
     */
    private Long orgId;
    /**
     * 组织单位名
     */
    private String orgName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户状态
     */
    private String userStatus;
}
