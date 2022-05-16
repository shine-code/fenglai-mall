package com.fenglai.common.web.exception;

import com.fenglai.common.web.response.R;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R error(MethodArgumentNotValidException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public R error(ConstraintViolationException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(BizException.class)
    public R error(BizException e) {
        e.printStackTrace();
        return R.error(e.getMessage());
    }
}
