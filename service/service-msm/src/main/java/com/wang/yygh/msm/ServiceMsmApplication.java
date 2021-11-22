package com.wang.yygh.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangyu
 * @date 2021/11/22 12:44
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)     //取消数据源自动配置
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.wang"})
public class ServiceMsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMsmApplication.class, args);
    }
}

