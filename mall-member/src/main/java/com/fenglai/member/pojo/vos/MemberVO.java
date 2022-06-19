package com.fenglai.member.pojo.vos;

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
public class MemberVO {

    private Long id;
    private String name;
}
