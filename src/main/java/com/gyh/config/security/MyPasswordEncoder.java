package com.gyh.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 加密 算法
 * 有待 完善
 * @author guoyh
 * @Date 2019/7/23 23:19
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
