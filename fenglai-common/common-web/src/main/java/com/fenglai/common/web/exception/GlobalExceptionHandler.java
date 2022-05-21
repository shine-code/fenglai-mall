package com.fenglai.common.web.exception;

import com.fenglai.common.web.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        if (e instanceof DataAccessException) {
            return error((DataAccessException) e);
        }
        log.error("操作失败: ", e);
        return R.error(e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public R error(BizException e) {
        log.error("操作异常: ", e);
        return R.error(e.getMessage());
    }

    /**
     * 数据库异常信息不适于展示在页面
     */
    @ExceptionHandler(DataAccessException.class)
    public R error(DataAccessException e) {
        log.error("操作异常: ", e);
        return R.error("请稍后重试! data access exception...!");
    }

    @ExceptionHandler(BindException.class)
    public R error(BindException e) {
        log.error("参数校验异常: ", e);
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return R.error(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R error(MethodArgumentNotValidException e) {
        log.error("参数校验异常: ", e);
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return R.error(errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public R error(ConstraintViolationException e) {
        log.error("参数校验异常: ", e);
        String errorMessage = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining());
        return R.error(errorMessage);
    }
}
