package com.fenglai.admin.pojo.vos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @description: 角色包含的用户列表
 * 
 * @author: TJ
 * @date:  2022-05-29
 **/
@Data
@Accessors(chain = true)
public class RoleRelateUserVO {

    private Long id;
    private String userCode;
    private String userName;
    private String phone;
    private LocalDate createTime;
}
