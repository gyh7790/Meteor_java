package com.gyh.system.sys.entity;

import java.io.Serializable;

/**
 * @author gyh
 * @Date 2020/10/22 23:21
 */
public class UserRole implements Serializable {

    private String userId;
    private String roleId;

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
