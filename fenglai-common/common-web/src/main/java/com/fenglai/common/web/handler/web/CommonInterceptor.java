package com.fenglai.common.web.handler.web;

import com.fenglai.common.core.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 打印接口入参和耗时
 * 
 * @author: TJ
 * @date:  2022-05-30
 **/
@Slf4j
public class CommonInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> TIME = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TIME.set(System.currentTimeMillis());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = TIME.get();
        log.info("[{}]参数: {}, 耗时: {}", request.getRequestURI(), JsonUtil.toJson(request.getParameterMap()), System.currentTimeMillis() - startTime);
        TIME.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
