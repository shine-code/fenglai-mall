package com.fenglai.common.web.response;

import lombok.Data;

/**
 * @description: 分页对象
 * 
 * @author: TJ
 * @date:  2022-05-22
 **/
@Data
public class Page {

    /**
     * 总记录数
     */
    private Long total;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

}
