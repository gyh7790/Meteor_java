package com.gyh.common.constant;

import com.gyh.system.sys.entity.User;

/**
 * @author gyh
 * @Date 2021/3/11 9:28
 */
public class UserLocal {

    private static final ThreadLocal<User> LOCAL = new ThreadLocal<User>();

    private static final ThreadLocal<String> LOCAL_VERSION = new ThreadLocal<>();

    private UserLocal() {}

    public static void set(User user) {
        LOCAL.set(user);
    }

    public static User get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

    public static void setV(String version){
        LOCAL_VERSION.set(version);
    }

    public static String getV(){
        return LOCAL_VERSION.get();
    }

}
