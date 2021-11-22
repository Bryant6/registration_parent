package com.wang.yygh.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyu
 * @date 2021/11/20 14:24
 */
@Configuration
@MapperScan("com.wang.yygh.user.mapper")
public class UserConfig {
}
