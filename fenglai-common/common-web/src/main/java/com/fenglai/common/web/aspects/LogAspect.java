package com.fenglai.common.web.aspects;

import com.fenglai.common.core.utils.JsonUtil;
import com.fenglai.common.web.annotations.Log;
import com.fenglai.common.web.pojo.logs.LogDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private Environment env;

    /**
     * 切点为使用 @Log注解标注的方法
     */
    @Pointcut("@annotation(com.fenglai.common.web.annotations.Log)")
    public void pointcut() { }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        LogDTO logDTO = handlerLog(joinPoint);
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        logDTO.setExecuteTime(System.currentTimeMillis() - start);
        logDTO.setResult(JsonUtil.toJson(proceed));
        log.info("common-log >>> {}", logDTO);

        return proceed;
    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {

        LogDTO logDTO = handlerLog(joinPoint);
        logDTO.setErrorMsg(ex.getMessage());

        log.info("common-log >>> {}", logDTO);
    }

    private LogDTO handlerLog(JoinPoint joinPoint) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURI();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logOperate = method.getAnnotation(Log.class);

        // 请求的类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        List<Object> argList = new ArrayList<>();
        for (Object arg : args) {
            // 跳过一些特殊的参数类型
            if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse
                    || arg instanceof MultipartFile || arg instanceof BindResult) {
                continue;
            }
            argList.add(arg);
        }

        // 日志对象
        return new LogDTO()
                .setApplicationName(env.getProperty("spring.application.name"))
                .setParam(JsonUtil.toJson(argList))
                .setMethodName(className + "#" + methodName)
                .setUrl(url)
                .setIp(getRemoteHost(request))
                .setDesc(logOperate.desc());
    }

    /**
     * 获取目标主机的ip
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
