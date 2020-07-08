package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;

/**
 * @author gyh
 * @Date 2020/6/27 21:09
 */
public class RoleUrl extends BaseEntity<RoleUrl> {

    private String roleId;
    private String urlId;

    public RoleUrl(String roleId, String urlId) {
        this.roleId = roleId;
        this.urlId = urlId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }
}
