package com.gyh.redis;

import com.gyh.common.tools.ListUtils;
import com.gyh.system.sys.dto.LoginUser;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

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
        LoginUser lu = null;
        List<LoginUser> list = new ArrayList<>();
        lu = new LoginUser();
        lu.setUsername("AAA");
        lu.setPassword("aaa");
        lu.setCaptcha("123");
        list.add(lu);
        lu = new LoginUser();
        lu.setUsername("BBB");
        lu.setPassword("bbb");
        lu.setCaptcha("456");
        list.add(lu);
        lu = new LoginUser();
        lu.setUsername("CCC");
        lu.setPassword("ccc");
        lu.setCaptcha("741");
        list.add(lu);
        lu = new LoginUser();
        lu.setUsername("AAA");
        lu.setPassword("aaa");
        lu.setCaptcha("852");
        list.add(lu);
        lu = new LoginUser();
        lu.setUsername("CCC");
        lu.setPassword("bbb");
        lu.setCaptcha("963");
        list.add(lu);

        LoginUser luser = new LoginUser();
        luser.setUsername("CCC");
        luser.setPassword("bbb");
        luser.setCaptcha("963");


        boolean ss = list.contains(luser);

        System.out.println(ss);

    }


}
