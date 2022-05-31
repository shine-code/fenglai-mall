package com.fenglai.common.web.pojo.logs;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-05-31
 **/
@Data
@Accessors(chain = true)
public class LogDTO {

    /**
     * 应用名
     */
    private String applicationName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 参数
     */
    private String param;
    /**
     * 接口返回结果
     */
    private String result;
    /**
     * 异常信息
     */
    private String errorMsg;
    /**
     * 客户端ip
     */
    private String ip;
    /**
     * 执行方法
     */
    private String methodName;
    /**
     * 请求url
     */
    private String url;
    /**
     * 接口执行时间
     */
    private Long executeTime;
    /**
     * 描述信息
     */
    private String desc;
}
