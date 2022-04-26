package com.shine.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-25
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.shine")
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
