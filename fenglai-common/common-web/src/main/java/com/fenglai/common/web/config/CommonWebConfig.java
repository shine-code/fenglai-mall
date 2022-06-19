package com.fenglai.common.web.config;

import com.fenglai.common.web.handler.web.CachingContentFilter;
import com.fenglai.common.web.handler.web.CommonInterceptor;
import com.fenglai.common.web.handler.web.PostParamMethodArgumentResolver;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

/**
 * @description: Web扩展配置
 * 
 * @author: TJ
 * @date:  2022-05-20
 **/
@Configuration
public class CommonWebConfig implements WebMvcConfigurer {

    /**
     * 增加参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PostParamMethodArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 参数校验快速失败
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    public FilterRegistrationBean<CachingContentFilter> cachingContentFilter() {
        FilterRegistrationBean<CachingContentFilter> registration = new FilterRegistrationBean<>(new CachingContentFilter());
        registration.addUrlPatterns("/*");
        registration.setName("cachingContentFilter");
        return registration;
    }
}
