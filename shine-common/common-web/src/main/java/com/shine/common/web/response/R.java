package com.shine.common.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class R implements Serializable {

    private String code;

    private String message;

    private Object data;

    private R(){}

    /**
     * 调用成功
     */
    public static R ok() {
        R r = new R();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }

    public static R ok(String code, String message, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    /**
     * 调用失败
     */
    public static R error() {
        R r = new R();
        r.setCode(ResultCode.SYSTEM_EXECUTION_ERROR.getCode());
        r.setMessage(ResultCode.SYSTEM_EXECUTION_ERROR.getMessage());
        return r;
    }

    public static R error(String code, String message) {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static R error(Object data) {
        R r = new R();
        r.setCode(ResultCode.SYSTEM_EXECUTION_ERROR.getCode());
        r.setMessage(ResultCode.SYSTEM_EXECUTION_ERROR.getMessage());
        r.setData(data);
        return r;
    }

    public static R error(String code, String message, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}
