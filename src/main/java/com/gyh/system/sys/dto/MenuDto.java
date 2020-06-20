package com.gyh.system.sys.dto;

import com.gyh.system.sys.entity.Menu;

import java.io.Serializable;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/20 15:54
 */
public class MenuDto implements Serializable {

    private String id;  // 编号
    private String parentId;  //父级编号
    private String name;  //名称
    private Integer sort;  //排序
    private String href;  //链接
    private String target;  //目标
    private String icon;  //图标
    private Boolean show;  //是否在菜单中显示

    private Boolean childShow; //展开子菜单
    private List<MenuDto> children; // 子级菜单
    private Boolean child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean getChildShow() {
        return childShow;
    }

    public void setChildShow(Boolean childShow) {
        this.childShow = childShow;
    }

    public List<MenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDto> children) {
        this.children = children;
    }

    public Boolean getChild() {
        return child;
    }

    public void setChild(Boolean child) {
        this.child = child;
    }
}
