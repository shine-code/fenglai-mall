package com.fenglai.common.web.exception;

import com.fenglai.common.web.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BizException extends RuntimeException{

    private String code;
    private String message;

    public BizException(String message) {
        this.code = ResultCode.SYSTEM_EXECUTION_ERROR.getCode();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BizException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
