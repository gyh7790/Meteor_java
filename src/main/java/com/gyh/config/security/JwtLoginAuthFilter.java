package com.gyh.config.security;

import com.gyh.common.tools.JsonUtils;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dto.LoginUser;
import com.gyh.system.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 暂时 未使用
 * @author gyh
 * @Date 2020/6/16 21:37
 */
public class JwtLoginAuthFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtLoginAuthFilter.class);

    /*
    AuthenticationManager： 用户认证的管理类，所有的认证请求（比如login）都会通过提交一个token给AuthenticationManager的authenticate()方法来实现。
    当然事情肯定不是它来做，具体校验动作会由AuthenticationManager将请求转发给具体的实现类来做。根据实现反馈的结果再调用具体的Handler来给用户以反馈。
     */
    private AuthenticationManager authenticationManager;

    public JwtLoginAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // 设置该过滤器地址
        super.setFilterProcessesUrl("/login");
    }

    /**
     * description: 登录验证
     *
     * @param request
     * @param response
     * @return org.springframework.security.core.Authentication
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(request.getParameter("username"));
        loginUser.setPassword(request.getParameter("password"));
        logger.debug(loginUser.toString());
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
        );

    }

    /**
     * 登录验证成功后调用，验证成功后将生成Token
     */
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication auth) throws IOException {
//
//        LoginUser jwtUser = (LoginUser) auth.getPrincipal();
//        logger.debug("JwtAuthUser:" + jwtUser.toString());
//        List<String> roles = new ArrayList<>();
//        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
//        for (GrantedAuthority authority : authorities){
//            roles.add(authority.getAuthority());
//        }
//        logger.debug("roles:"+roles);
//        User user = new User(jwtUser);
//        String token = JwtTokenUtils.generateToken(jwtUser.getId(), jwtUser.getUsername(), roles,true);
//        logger.debug("token:"+token);
//        resp.setContentType("application/json;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write(JsonUtils.toStrByJson(R.ok("登入成功").put("token",JwtTokenUtils.TOKEN_PREFIX+token)));
//        out.flush();
//        out.close();
//    }
//
//    /**
//     * description: 登录验证失败后调用，这里直接Json返回，实际上可以重定向到错误界面等
//     * 与AuthenticationFailureHandler作用相同
//     *
//     * @param request
//     * @param failed
//     * @return void
//     */
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
//        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("application/json; charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write(JsonUtils.toStrByJson(R.ok(201,"用户名 或 密码有误...")));
//        out.flush();
//        out.close();
//    }
}
