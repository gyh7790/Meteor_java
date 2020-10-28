package com.gyh.test;

import com.gyh.common.tools.StringUtils;
import io.netty.util.internal.StringUtil;

/**
 * @author gyh
 * @Date 2020/10/27 21:13
 */
public class Test {

    public static void main(String[] args) {

        String str = "A0001";

        String a = StringUtils.add(StringUtils.right(str,3),"1");
        for (int i = 0; i < 2000; i++) {
            char prefix = str.charAt(0);
            String suffix = StringUtils.right(str,3);
            String nextSuffix = StringUtils.add(suffix,"1");
            if (StringUtils.length(nextSuffix) > 3) {
                str = (char)(prefix + 1) + "001";
            } else {
                str = prefix + StringUtils.leftPad(nextSuffix,3,"0");
            }
            System.out.println(str);
        }
    }
}
