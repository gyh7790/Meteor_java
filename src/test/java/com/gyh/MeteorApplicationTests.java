package com.gyh;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@MapperScan("com.gyh.*.*.dao")
class MeteorApplicationTests {

    @Test
    void contextLoads() {
    }

}
