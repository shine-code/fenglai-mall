package com.fenglai.admin.pojo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-05-21
 **/
@Data
@Accessors(chain = true)
public class QueryUserDTO {

    /**
     * id集合, 选中数据导出时传来
     */
    private List<Long> ids;
    /**
     * 关键词: 编码、名称、手机号
     */
    private String keyword;
    /**
     * 组织单位ID
     */
    private Long orgId;

}
