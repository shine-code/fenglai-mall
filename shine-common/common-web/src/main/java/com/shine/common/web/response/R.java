package com.shine.common.web.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class R implements Serializable {

    private String code;

    private String message;

    private Object data;

    private R() {
    }

    /**
     * 调用成功
     */
    public static R ok() {
        return restResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static R ok(Object data) {
        return restResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static R ok(String code, String message, Object data) {
        return restResult(code, message, data);

    }

    /**
     * 调用失败
     */
    public static R error() {
        return restResult(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMessage(), null);
    }

    public static R error(String code, String message) {
        return restResult(code, message, null);

    }

    public static R error(Object data) {
        return restResult(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMessage(), data);
    }

    public static R error(String code, String message, Object data) {
        return restResult(code, message, data);

    }

    private static R restResult(String code, String msg, Object data) {
        R apiResult = new R();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }
}
