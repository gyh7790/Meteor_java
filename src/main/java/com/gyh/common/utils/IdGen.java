package com.gyh.common.utils;

import org.apache.catalina.SessionIdGenerator;
import org.springframework.util.IdGenerator;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * @author gyh
 * @Date 2020/6/12 17:21
 */
public class IdGen implements IdGenerator {

    private static SecureRandom random = new SecureRandom();

    /**
     * 获取 UUID
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public UUID generateId() {
        return UUID.randomUUID();
    }

    public static void main(String[] args) {

    }

}
