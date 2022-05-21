package com.fenglai.admin.pojo.enums;

import com.fenglai.common.web.pojo.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 系统用户状态枚举
 * 
 * @author: TJ
 * @date:  2022-05-19
 **/
@AllArgsConstructor
public enum UserStatusEnum implements IBaseEnum<Integer> {

    ENABLE(0, "已启用"),
    DISABLE(1, "已禁用"),
    DELETED(2, "已删除");

    @Getter
    private final Integer value;
    @Getter
    private final String label;

}
