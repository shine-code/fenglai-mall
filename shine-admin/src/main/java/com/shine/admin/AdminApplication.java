package com.shine.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-25
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
