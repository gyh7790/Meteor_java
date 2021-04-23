package com.gyh.redis;

import com.gyh.common.tools.ListUtils;
import com.gyh.system.sys.dto.LoginUser;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gyh
 * @Date 2020/6/14 11:57
 */
public class RedisTest {
    public static void main(String[] arg){
        BigDecimal big = BigDecimal.valueOf(0.0); //设置BigDecimal初始值
//        big.setScale(2);  // 保留1位小数，默认用四舍五入。
//        big.setScale(1, BigDecimal.ROUND_DOWN);  // 直接删除多余的小数，2.3513直接被截断位2.3

        System.out.println(big);

    }


}
