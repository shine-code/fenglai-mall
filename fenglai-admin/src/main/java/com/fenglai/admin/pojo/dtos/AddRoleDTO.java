package com.fenglai.admin.pojo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @description: 新增角色DTO
 * 
 * @author: TJ
 * @date:  2022-05-28
 **/
@Data
@Accessors(chain = true)
public class AddRoleDTO {

    @NotBlank(message = "角色名不能为空")
    @Length(max = 20, message = "角色名不超过20字")
    private String roleName;

    @NotBlank(message = "角色状态不能为空")
    private String roleStatus;

    private Integer sort;
}
