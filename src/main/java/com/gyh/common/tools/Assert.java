package com.gyh.common.tools;

import com.gyh.common.exception.RRException;

/**
 * @author gyh
 * @Date 2020/6/16 20:45
 */
public class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}