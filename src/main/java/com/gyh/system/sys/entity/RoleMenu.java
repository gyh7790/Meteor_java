package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;

import java.io.Serializable;

/**
 * @author gyh
 * @Date 2020/6/25 19:30
 */
public class RoleMenu extends BaseEntity<RoleMenu> {

    private String roleId;
    private String menuId;

    public RoleMenu() {}

    public RoleMenu(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
