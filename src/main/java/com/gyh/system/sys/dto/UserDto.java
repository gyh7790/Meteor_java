package com.gyh.system.sys.dto;

import com.gyh.modules.test.dto.TestDto;
import com.gyh.system.sys.entity.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/10/21 23:37
 */
public class UserDto implements Serializable {

    private String id;
    private String loginName; // 登入名称
    private String nickName;    // 昵称
    private String password;    // 密码
    private String name;    // 用户名称
    private String email;   // 邮箱
    private Integer sex;    // 性别：1男，2女
    private Integer type;   // 用户类型
    private String phone;   // 手机号
    private Integer status; // 状态

    private List<Role> roles = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
