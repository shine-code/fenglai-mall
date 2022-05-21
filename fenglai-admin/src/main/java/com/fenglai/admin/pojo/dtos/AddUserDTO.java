package com.fenglai.admin.pojo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
     * 用户id
     */
    private Long id;
    /**
     * 用户编码
      */
    @NotBlank(message = "用户编码不能为空")
    @Length(max = 20, message = "用户编码不超过20")
    private String userCode;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不超过20字")
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
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 关联角色id集合
     */
    private List<Long> roleIds;
}
