package com.gyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCaching
@SpringBootApplication
@MapperScan("com.gyh.*.*.dao")
public class MeteorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeteorApplication.class, args);
    }

}
