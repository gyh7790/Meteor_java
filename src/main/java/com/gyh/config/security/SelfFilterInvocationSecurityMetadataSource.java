package com.gyh.config.security;

import com.gyh.system.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 动态获取url权限配置
 * @author gyh
 * @Date 2020/6/14 21:32
 */
@Component
public class SelfFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    public static final Logger logger = LoggerFactory.getLogger(SelfFilterInvocationSecurityMetadataSource.class);

    @Autowired
    private UserService userService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        logger.debug("执行............动态URL校验.....................................");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        Set<ConfigAttribute> set = new HashSet<>();
        SecurityConfig securityConfig = new SecurityConfig("URL_ROLE_common");
        set.add(securityConfig);
        if (ObjectUtils.isEmpty(set)) {
            return SecurityConfig.createList("ROLE_LOGIN");
        }
        return set;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
