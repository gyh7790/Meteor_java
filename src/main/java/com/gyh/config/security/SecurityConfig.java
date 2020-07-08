package com.gyh.config.security;

import com.gyh.common.constant.HttpStatus;
import com.gyh.common.tools.JsonUtils;
import com.gyh.common.tools.ListUtils;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dto.LoginUser;
import com.gyh.system.sys.dto.MenuDto;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.service.MenuService;
import com.gyh.system.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Security(安全框架)  配置
 * @author guoyh
 * @Date 2019/6/3 22:13
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SelfFilterInvocationSecurityMetadataSource selfFilterInvocationSecurityMetadataSource; //动态获取url权限配置

    @Resource
    private SelfAccessDecisionManager selfAccessDecisionManager; //权限校验

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    /**
     * 静态 文件处理
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        logger.debug("执行...WebSecurity .............................");
        web.ignoring().antMatchers("/image/**");
    }

    /**
     * 用户 授权操作
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("执行...用户授权操作 .............................");

        // 防止跨域请求伪造
        http.cors().and().csrf().disable();

        http.authorizeRequests().antMatchers("/login","/login_not").permitAll()
            .anyRequest().authenticated()
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                    o.setSecurityMetadataSource(selfFilterInvocationSecurityMetadataSource); //动态获取url权限配置
                    o.setAccessDecisionManager(selfAccessDecisionManager); //权限判断
                    return o;
                }
            })
            .and().formLogin().successHandler(successHandler).failureHandler(failureHandler)
            .and().logout().permitAll();

        // 未登入 处理
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        // 关闭 会话管理
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 添加 拦截器
        http.addFilter(new JwtPreAuthFilter(authenticationManager()));;

        // 异常处理
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }


    /**
     * 用户认证操作
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("执行......用户认证操作........................");
        auth.userDetailsService(userService).passwordEncoder(new MyPasswordEncoder());
    }

    // 认证 成功以后处理
    AuthenticationSuccessHandler successHandler = (req, resp, auth)->{

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象，这里是JwtAuthUser
        LoginUser jwtUser = (LoginUser) auth.getPrincipal();

        logger.debug("JwtAuthUser:" + jwtUser.toString());

        List<String> roleId = new ArrayList<>();
        // 角色 非空时 获取菜单
        if (ListUtils.isNotEmpty(jwtUser.getRoles())) {
            roleId = jwtUser.getRoles().stream().map(Role::getId).collect(Collectors.toList());
        }

        List<MenuDto> menuList = menuService.getListByRoles(roleId);

        logger.debug("roles:"+roleId);
        String token = JwtTokenUtils.generateToken(jwtUser.getUsername(), roleId, true);
        logger.debug("token:"+token);

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JsonUtils.toStrByJson(R.ok("登录成功").put("token",JwtTokenUtils.TOKEN_PREFIX+token).put("navList",menuList)));
        out.flush();
        out.close();
    };

    // 认证 失败以后处理
    AuthenticationFailureHandler failureHandler = (req, resp, auth)->{
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JsonUtils.toStrByJson(R.ok(201,"用户名 或 密码有误!!!")));
        out.flush();
        out.close();
    };

    // 拒绝 访问处理
    AccessDeniedHandler accessDeniedHandler = (req, resp, e)->{
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JsonUtils.toStrByJson(R.ok(403,"权限不足")));
        out.flush();
        out.close();
    };

    // 退出 处理
    LogoutSuccessHandler logoutSuccessHandler = (req, resp, e)->{
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(JsonUtils.toStrByJson(R.ok(HttpStatus.SC_OK,"用户已退出")));
        out.flush();
        out.close();
    };

    //自定义未登录时JSON数据
    AuthenticationEntryPoint authenticationEntryPoint = (req, resp, e)->{
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JsonUtils.toStrByJson(R.error(HttpStatus.SC_UNAUTHORIZED,"请登入系统")));
        out.flush();
        out.close();
    };



}
