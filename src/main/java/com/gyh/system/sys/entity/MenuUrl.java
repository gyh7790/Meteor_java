package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;

/**
 * @author gyh
 * @Date 2020/6/26 19:25
 */
public class MenuUrl extends BaseEntity<MenuUrl> {

    private String menuId;
    private String name;
    private String url;
    private Integer type;
    private Integer auth;  //是否参与权限校验

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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

    @Override
    public String toString() {
        return "MenuUrl{" +
                "menuId='" + menuId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", auth=" + auth +
                '}';
    }
}
