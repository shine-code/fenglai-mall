package com.fenglai.common.web.exception;

import com.fenglai.common.web.response.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public R error(BizException e) {
        e.printStackTrace();
        return R.error(e.getMessage());
    }
}
