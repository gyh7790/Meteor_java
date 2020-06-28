package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;

/**
 * @author gyh
 * @Date 2020/6/27 21:09
 */
public class RoleUrl extends BaseEntity<RoleUrl> {

    private String roleId;
    private String menuUrlId;

    public RoleUrl(String roleId, String menuUrlId) {
        this.roleId = roleId;
        this.menuUrlId = menuUrlId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuUrlId() {
        return menuUrlId;
    }

    public void setMenuUrlId(String menuUrlId) {
        this.menuUrlId = menuUrlId;
    }
}
