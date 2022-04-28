package com.shine.common.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 文件服务
 * 
 * @author: TJ
 * @date:  2022-04-28
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CommonFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonFileApplication.class, args);
    }
}
