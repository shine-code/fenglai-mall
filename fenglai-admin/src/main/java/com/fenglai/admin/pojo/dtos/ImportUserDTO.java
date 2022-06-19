package com.fenglai.admin.pojo.dtos;

import com.fenglai.common.core.excel.Excel;
import lombok.Data;

/**
 * @description: 用户导入DTO
 * 
 * @author: TJ
 * @date:  2022-05-24
 **/
@Data
public class ImportUserDTO {

    @Excel(columnName = "用户编码", required = true, max = 20)
    private String userCode;
    @Excel(columnName = "用户名", required = true, max = 20)
    private String userName;
    private String nickName;
    @Excel(columnName = "手机号", required = true)
    private String phone;
    private String orgId;
    private String sex;
    private String email;
}
