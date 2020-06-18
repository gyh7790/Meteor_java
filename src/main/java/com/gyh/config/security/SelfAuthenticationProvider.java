package com.gyh.config.security;

import com.gyh.system.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 登入 认证
 * @author gyh
 * @Date 2020/6/14 21:28
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal(); //表单输入的用户名
        String password = (String) authentication.getCredentials(); //表单输入的密码

        UserDetails userInfo = userService.loadUserByUsername(username);

        boolean matches = password.equals(userInfo.getPassword());
        if (!matches) {
            throw new BadCredentialsException("The password is incorrect!!");
        }
        return new UsernamePasswordAuthenticationToken(username, userInfo.getPassword(), userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}