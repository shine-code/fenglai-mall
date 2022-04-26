package com.shine.common.web.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    /**
     * 切点为使用 @Log注解标注的方法
     */
    @Pointcut("@annotation(com.shine.common.web.annotations.Log)")
    public void pointcut() { }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        return null;
    }
}
