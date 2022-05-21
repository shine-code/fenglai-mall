package com.fenglai.common.web.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class R implements Serializable {

    /**
     * 状态码
     */
    private String code;
    /**
     * 返回提示信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
    /**
     * 分页对象
     */
    private Page page;

    private R() {
    }

    /**
     * 调用成功
     */
    public static R ok() {
        return restResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null, null);
    }

    public static R ok(Object data) {
        return restResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, null);
    }

    public static R ok(Object data, Page page) {
        return restResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, page);
    }

    public static R ok(String code, String message, Object data) {
        return restResult(code, message, data, null);
    }

    public static R ok(String code, String message, Object data, Page page) {
        return restResult(code, message, data, page);
    }

    /**
     * 调用失败
     */
    public static R error() {
        return restResult(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMessage(), null, null);
    }

    public static R error(String code, String message) {
        return restResult(code, message, null, null);

    }

    public static R error(Object data) {
        return restResult(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMessage(), data, null);
    }

    public static R error(String code, String message, Object data) {
        return restResult(code, message, data, null);
    }

    public static R judge(boolean status) {
        if (status) {
            return ok();
        } else {
            return error();
        }
    }

    private static R restResult(String code, String msg, Object data, Page page) {
        R result = new R();
        result.setCode(code);
        result.setData(data);
        result.setMessage(msg);
        result.setPage(page);
        return result;
    }
}
