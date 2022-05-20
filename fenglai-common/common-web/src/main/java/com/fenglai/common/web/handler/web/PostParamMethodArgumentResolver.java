package com.fenglai.common.web.handler.web;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.fenglai.common.core.utils.JsonUtil;
import com.fenglai.common.web.annotations.PostParam;
import com.fenglai.common.web.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 解析@PostParam注解绑定参数
 * 
 * @author: TJ
 * @date:  2022-05-20
 **/
@Slf4j
@SuppressWarnings("all")
public class PostParamMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 判断是否需要处理该参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PostParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (servletRequest == null) {
            log.warn("HttpServletRequest is null...");
            return null;
        }

        checkRequest(servletRequest);

        // 获取请求body
        String requestBody = ServletUtil.getBody(servletRequest);
        Map<String, Object> params = JsonUtil.fromJsonToMap(requestBody);
        params = MapUtil.isEmpty(params) ? new HashMap<>(0) : params;

        // 获取注解指定的参数值
        PostParam postParam = parameter.getParameterAnnotation(PostParam.class);
        String name = StrUtil.isBlank(postParam.value()) ? parameter.getParameterName() : postParam.value();
        Object value = params.get(name);
        try {
            value = Convert.convert(parameter.getParameterType(), value);
        } catch (Exception e) {
            throw new BizException("该参数类型不匹配: " + name);
        }
        return value;
    }

    private void checkRequest(HttpServletRequest servletRequest) {

        String contentType = servletRequest.getContentType();
        if (contentType == null || !contentType.contains("application/json")) {
            throw new BizException("解析参数异常，contentType需为：application/json");
        }

        if (!"post".equalsIgnoreCase(servletRequest.getMethod())) {
            throw new BizException("解析参数异常，请求类型必须为post");
        }
    }
}
