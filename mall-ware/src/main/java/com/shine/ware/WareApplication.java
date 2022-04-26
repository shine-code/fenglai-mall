package com.shine.ware;

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
public class WareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WareApplication.class, args);
    }
}
