package com.gyh.config.security;

import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/16 21:38
 */
public class JwtPreAuthFilter extends BasicAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtLoginAuthFilter.class);

    public JwtPreAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * description: 从request的header部分读取Token
     *
     * @param request
     * @param response
     * @param chain
     * @return void
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader("token");

        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    /**
     * description: 读取Token信息，创建UsernamePasswordAuthenticationToken对象
     *
     * @param tokenHeader
     * @return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        //解析Token时将“Bearer ”前缀去掉
        String token = tokenHeader.replace(JwtUtils.TOKEN_PREFIX, "");
        String userId = JwtUtils.getUserId(token);
        String username = JwtUtils.getUsername(token);
        List<String> roles = JwtUtils.getUserRole(token);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (roles!=null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        if (username != null){
            return new UsernamePasswordAuthenticationToken(new User(userId,username,roles), null, authorities);
        }
        return null;
    }
}
