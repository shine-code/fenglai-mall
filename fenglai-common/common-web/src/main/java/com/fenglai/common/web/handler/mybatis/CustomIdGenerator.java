package com.fenglai.common.web.handler.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.fenglai.common.core.utils.IdUtil;
import org.springframework.stereotype.Component;

/**
 * @description: 自定义生成 15 位雪花ID
 * 
 * @author: TJ
 * @date:  2022-05-13
 **/
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return IdUtil.nextId();
    }
}
