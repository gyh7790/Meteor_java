package com.gyh.system.sys.dto;

import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2020/6/14 21:02
 */
public class LoginUser implements UserDetails, Serializable {
    private String id;
    private String username;
    private String password;
    private String captcha;

    private List<Role> roles = new ArrayList<>();

    private Set<? extends GrantedAuthority> authorities;

    public String getId() {
        return id;
    }

    public LoginUser(){}

    public LoginUser(User user){
        this.id=user.getId();
        this.username=user.getLoginName();
        this.password=user.getPassword();
        this.roles = user.getRoles();
    }

    public LoginUser(User user,List<Role> roles){
        this.id=user.getId();
        this.username=user.getLoginName();
        this.password=user.getPassword();
        this.roles=roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (roles!=null) {
            roles.stream().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getId()));
            });
        }
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginUser loginUser = (LoginUser) o;
        return username.equals(loginUser.username) &&
                password.equals(loginUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
