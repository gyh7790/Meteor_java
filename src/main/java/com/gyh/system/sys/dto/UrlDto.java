package com.gyh.system.sys.dto;

import com.gyh.common.tools.ListUtils;
import com.gyh.common.tools.StringUtils;
import com.gyh.system.sys.entity.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2020/10/26 23:05
 */
public class UrlDto {

    private String id;
    private String code;
    private String name;
    private String url;
    private String menuId;
    private String remarks;
    private List<String> roles;

    private String menuName;
    private Integer type;
    private Integer auth;  //是否参与权限校验
    private String permission; // 授权字段

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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

    public void setCode(String code) {
        this.code = code;
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setRoleIds(String roleIds){
        if (StringUtils.isNotEmpty(roleIds) && ListUtils.isEmpty(this.roles)){
            String[] roleIdList = StringUtils.split(roleIds,",");
            this.roles = Arrays.asList(roleIdList);
        }
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
