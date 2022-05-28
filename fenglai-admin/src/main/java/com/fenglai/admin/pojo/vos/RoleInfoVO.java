package com.fenglai.admin.pojo.vos;

import com.alibaba.excel.annotation.ExcelIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-05-28
 **/
@Data
@Accessors(chain = true)
public class RoleInfoVO {

    @ExcelIgnore
    private Long id;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色状态
     * {@link com.fenglai.admin.pojo.enums.SysStatusEnum}
     */
    private String roleStatus;
    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 显示顺序
     */
    @ExcelIgnore
    private Integer sort;
}
