package com.fenglai.admin.pojo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: 新增用户DTO
 * 
 * @author: TJ
 * @date:  2022-05-15
 **/
@Data
@Accessors(chain = true)
public class AddUserDTO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 组织id
     */
    private Long orgId;

    /**
     * 性别,0:女,1:男
     */
    private Integer sex;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;
}
