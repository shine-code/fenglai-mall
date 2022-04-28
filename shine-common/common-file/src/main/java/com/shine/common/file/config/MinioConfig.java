package com.shine.common.file.config;

import cn.hutool.core.util.StrUtil;
import com.shine.common.file.exception.FileServiceException;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-28
 **/
@Component
@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioConfig {

    /**
     * 连接地址
     */
    private String endpoint;
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;
    /**
     * 自定义域名, 非必须
     */
    private String customDomain;
    /**
     * 默认存储桶名称
     */
    private String defaultBucketName;

    @Bean
    public MinioClient minioClient() {
        if (StrUtil.isBlank(endpoint)) {
            throw new FileServiceException("minio服务地址为空");
        }
        if (StrUtil.isBlank(accessKey)) {
            throw new FileServiceException("minio服务accessKey为空");
        }
        if (StrUtil.isBlank(secretKey)) {
            throw new FileServiceException("minio服务secretKey为空");
        }
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
