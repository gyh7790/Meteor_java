package com.gyh.config.security;

import com.gyh.common.constant.Constant;
import com.gyh.common.tools.ListUtils;
import com.gyh.common.tools.StringUtils;
import com.gyh.system.sys.dto.UrlDto;
import com.gyh.system.sys.service.UrlService;
import com.gyh.system.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
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

    @Autowired
    private UrlService urlService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Value("${spring.security.url.auth.null}")
    private boolean isAuth;

    /**
     *
     * 此方法是为了判断用户请求的接口url,是否在权限列表中
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        Set<ConfigAttribute> set = new HashSet<>();

        logger.debug("执行............动态URL校验.....................................");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 获取请求地址
        String requestUrl = StringUtils.substringBefore(((FilterInvocation) o).getRequestUrl(),"?");

        List<UrlDto> urlList = urlService.getAuthIgnoreUri();

        // 获取不需要校验的接口
        List<String> ignoreUriList = urlList.parallelStream().filter(e -> Constant.IsAuth.NOT.getValue() == e.getAuth()).map(UrlDto::getUrl).collect(Collectors.toList());

        if (ListUtils.containsMatchUrl(ignoreUriList,requestUrl)) {
            // set.add(new SecurityConfig("URL_ROLE_common"));
            // 返回 null 不再执行权限校验
            return null;
        }

        // 获取该用户所拥有的的接口（不需要验证的接口除外）
        Optional<UrlDto> authUri = urlList.parallelStream().filter(e -> Constant.IsAuth.AUTH.getValue() == e.getAuth() && StringUtils.matchUrl(e.getUrl(),requestUrl)).findFirst();
        if (authUri.isPresent() && ListUtils.isNotEmpty(authUri.get().getRoles())) {
            authUri.get().getRoles().parallelStream().forEach(e->{
                set.add(new SecurityConfig(e));
            });
            return set;
        }

        // 系统中 未记录该 url
        if (isAuth) {
            throw new AccessDeniedException("权限不足!!");
        } else {
            return null;
        }
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
