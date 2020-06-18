package com.gyh.system.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gyh.common.persistence.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2020/6/14 0:03
 */
public class Role extends BaseEntity<Role> {

    // 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
    public static final int DATA_SCOPE_ALL = 1;
    public static final int DATA_SCOPE_COMPANY_AND_CHILD = 2;
    public static final int DATA_SCOPE_COMPANY = 3;
    public static final int DATA_SCOPE_OFFICE_AND_CHILD = 4;
    public static final int DATA_SCOPE_OFFICE = 7;
    public static final int DATA_SCOPE_SELF = 8;
    public static final int DATA_SCOPE_CUSTOM = 9;

    private String name;  // 角色名称
    private String enname;  // 英文名称
    private String type;  // 角色类型
    private Integer dataScope;  // 数据范围
    private String useable;  // 是否可用

    private List<Menu> menuList = new ArrayList<>(); // 拥有菜单列表

    public Role() {
        super();
    }

    public Role(String id){
        super(id);
    }

    @Length(min=1, max=100, message="角色名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min=0, max=255, message="英文名称长度必须介于 0 和 255 之间")
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    @Length(min=0, max=255, message="角色类型长度必须介于 0 和 255 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min=0, max=1, message="数据范围长度必须介于 0 和 1 之间")
    public Integer getDataScope() {
        return dataScope;
    }

    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
    }

    @Length(min=0, max=64, message="是否可用长度必须介于 0 和 64 之间")
    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    @JsonIgnore
    public List<Menu> getMenuList() {
        return menuList;
    }

    public List<String> getMenuIdList() {
        return menuList.stream().map(Menu::getId).collect(Collectors.toList());
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

}
