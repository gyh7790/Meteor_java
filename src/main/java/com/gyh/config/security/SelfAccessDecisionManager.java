package com.gyh.config.security;

import com.gyh.common.tools.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限校验
 * @author gyh
 * @Date 2020/6/14 21:19
 */
@Component
public class SelfAccessDecisionManager implements AccessDecisionManager {

    private final Logger logger = LoggerFactory.getLogger(SelfAccessDecisionManager.class);

    /**
     *
     * 该方法url请求时才会调用，服务器启动时不会执行该方法
     * 每次请求 url 时会调用，返回这个url所需要的权限 或 角色
     * @param authentication
     * @param o
     * @param collection
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        logger.debug("执行.........权限校验.................................");

        List<String> authorityList = authentication.getAuthorities().parallelStream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<String> attributeList = collection.parallelStream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());

        // 所拥有的角色有交集
        if (ListUtils.isIntersection(authorityList,attributeList)) {
            return;
        }

        throw new AccessDeniedException("权限不足!!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
