package com.fenglai.admin.pojo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

/**
 * @description: 角色查询DTO
 * 
 * @author: TJ
 * @date:  2022-05-28
 **/
@Data
@Accessors(chain = true)
public class QueryRoleDTO {

    /**
     * id集合, 选中数据导出时传来
     */
    private List<Long> ids;
    /**
     * 关键词: 编码、名称
     */
    private String keyword;
    /**
     * 创建起始时间
     */
    private LocalDate createStartTime;
    /**
     * 创建结束时间
     */
    private LocalDate createEndTime;
}
