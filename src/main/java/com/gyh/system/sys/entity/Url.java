package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;
import com.gyh.common.tools.ListUtils;
import com.gyh.common.tools.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/26 19:25
 */
public class Url extends BaseEntity<Url> {

    private String menuId;
    private String code;
    private String menuName;
    private String name;
    private String url;
    private Integer type;
    private Integer auth;  //是否参与权限校验
    private String permission; // 授权字段

    private List<String> roles;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setRoleIds(String roleIds) {
        this.roles = StringUtils.getListSplit(roleIds,",");
    }

    @Override
    public String toString() {
        return "Url{" +
                "menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", auth=" + auth +
                '}';
    }
}
