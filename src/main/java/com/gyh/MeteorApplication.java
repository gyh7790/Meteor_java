package com.gyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guoyh
 * @version 2020-5-23
 */
@RestController
@EnableCaching
@MapperScan("com.gyh.*.*.dao")
@SpringBootApplication()
@EnableTransactionManagement(proxyTargetClass = true)
public class MeteorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeteorApplication.class, args);
    }

}


