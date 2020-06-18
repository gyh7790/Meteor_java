package com.gyh.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限校验
 * @author gyh
 * @Date 2020/6/14 21:19
 */
@Component
public class SelfAccessDecisionManager implements AccessDecisionManager {

    private final Logger logger = LoggerFactory.getLogger(SelfAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        logger.debug("执行.........权限校验.................................");

        if (true) {
            return;
        }


        throw new AccessDeniedException("SimpleGrantedAuthority!!");
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
