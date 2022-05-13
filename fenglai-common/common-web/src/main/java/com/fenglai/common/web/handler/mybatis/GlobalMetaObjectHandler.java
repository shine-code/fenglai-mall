package com.fenglai.common.web.handler.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description: mybatis-plus设置默认时间和操作人
 * 
 * @author: TJ
 * @date:  2022-05-13
 **/
@Component
public class GlobalMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasSetter("updateTime")) {
            this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")) {
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        }
    }
}
