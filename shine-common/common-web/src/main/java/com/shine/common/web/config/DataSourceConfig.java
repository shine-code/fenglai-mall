package com.shine.common.web.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean("mysqlDS")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mysql")
    public DataSource mysqlDS() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlJdbc")
    public JdbcTemplate mysqlJdbc(
            @Qualifier("mysqlDS") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
