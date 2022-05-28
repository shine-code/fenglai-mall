package com.fenglai.admin.pojo.enums;

import com.fenglai.common.web.pojo.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 用户性别
 * 
 * @author: TJ
 * @date:  2022-05-28
 **/
@AllArgsConstructor
public enum UserSexEnum implements IBaseEnum<Integer> {

    WOMAN(0, "女"),
    MAN(1, "男");

    @Getter
    private final Integer value;
    @Getter
    private final String label;
}
