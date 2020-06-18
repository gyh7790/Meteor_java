package com.gyh.config.mybatis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author gyh
 * @Date 2020/6/12 10:42
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MybatisConfig {

}
