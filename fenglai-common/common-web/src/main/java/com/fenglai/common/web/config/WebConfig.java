package com.fenglai.common.web.config;

import com.fenglai.common.web.handler.web.BodyReaderFilter;
import com.fenglai.common.web.handler.web.PostParamMethodArgumentResolver;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
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
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PostParamMethodArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
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
    public FilterRegistrationBean<BodyReaderFilter> bodyReaderFilterRegistration() {
        FilterRegistrationBean<BodyReaderFilter> registration = new FilterRegistrationBean<>(new BodyReaderFilter());
        registration.addUrlPatterns("/*");
        registration.setName("bodyReaderFilter");
        return registration;
    }
}
