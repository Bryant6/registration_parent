package com.wang.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangyu
 * @date 2021/9/15 19:13
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.wang")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.wang")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
