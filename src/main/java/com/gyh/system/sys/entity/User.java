package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/12 17:02
 */
public class User extends BaseEntity<User> {

    private String loginName; // 登入名称
    private String password;    // 密码
    private String name;    // 用户名称
    private String email;   // 邮箱
    private Integer sex;    // 性别：1男，2女
    private Integer type;   // 用户类型
    private String phone;   // 手机号
    private Integer status; // 状态

    private List<Role> roles = new ArrayList<>();

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ",loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", type=" + type +
                ", photo='" + phone + '\'' +
                ", status=" + status +
                ", remarks='" + remarks + '\'' +
                ", del=" + del +
                '}';
    }
}
