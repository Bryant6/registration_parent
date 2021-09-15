package com.wang.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyu
 * @date 2021/9/15 19:56
 */
@Configuration
@MapperScan("com.wang.yygh.hosp.mapper")
public class HospConfig {
}
