package com.fenglai.common.web.handler.web;

import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: 包装HttpServletRequest, 使其中的流数据可重复读取
 * 
 * @author: TJ
 * @date:  2022-05-21
 **/
public class CachingContentFilter implements Filter {

    private static final String FORM_CONTENT_TYPE = "multipart/form-data";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            String contentType = servletRequest.getContentType();
            // 表单类型数据不作包装, 否则上传文件时 MultipartFile 为null
            if (contentType != null && contentType.contains(FORM_CONTENT_TYPE)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletRequest requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
                filterChain.doFilter(requestWrapper, servletResponse);
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
