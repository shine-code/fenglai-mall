package com.fenglai.admin.pojo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-19
 **/
@Data
@Accessors(chain = true)
public class MemListVO {

    private String name;
    private String a;
}
