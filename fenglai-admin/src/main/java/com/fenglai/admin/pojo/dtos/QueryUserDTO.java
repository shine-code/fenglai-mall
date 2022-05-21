package com.fenglai.admin.pojo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

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
     * 关键词: 编码、名称、手机号
     */
    private String keyword;
    /**
     * 组织单位ID
     */
    private Long orgId;

}
