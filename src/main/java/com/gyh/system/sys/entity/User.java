package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;
import com.gyh.common.tools.ListUtils;
import com.gyh.common.tools.StringUtils;
import com.gyh.system.sys.dto.LoginUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2020/6/12 17:02
 */
public class User extends BaseEntity {

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

    public User(){}
    public User(LoginUser loginUser){
        this.id = loginUser.getId();
        this.loginName = loginUser.getUsername();
        this.roles = loginUser.getRoles();
    }

    public User(String userId,String userName,List<String> roleIds){
        this.id = userId;
        this.loginName = userName;
        if (ListUtils.isNotEmpty(roleIds)) {
            roleIds.stream().forEach(e->{
                this.roles.add(new Role(e));
            });
        }
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

    public void setRoleIds(String roleIds) {
        if (StringUtils.isNotEmpty(roleIds) && ListUtils.isEmpty(this.roles)){
            List<Role> roleList = new ArrayList<>();
            String[] roleIdList = StringUtils.split(roleIds,",");
            for (String str:roleIdList) {
                roleList.add(new Role(str));
            }
            this.roles = roleList;
        }
    }

    public List<String> getRoleIds() {
        if (ListUtils.isNotEmpty(this.roles)) {
            return this.roles.stream().map(Role::getId).collect(Collectors.toList());
        }
        return null;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
