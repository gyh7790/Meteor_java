package com.gyh.system.sys.dto;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/10/24 22:43
 */
public class RoleDto {

    private String id;
    private List<String> menuIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }
}
