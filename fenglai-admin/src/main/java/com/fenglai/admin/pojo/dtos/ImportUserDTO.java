package com.fenglai.admin.pojo.dtos;

import com.fenglai.common.core.annotations.Excel;
import lombok.Data;

/**
 * @description: 用户导入DTO
 * 
 * @author: TJ
 * @date:  2022-05-24
 **/
@Data
public class ImportUserDTO {

    @Excel(required = true, max = 20)
    private String userCode;
    @Excel(required = true, max = 20)
    private String userName;
    private String nickName;
    @Excel(required = true)
    private String phone;
    private String orgId;
    private String sex;
    private String email;
}
