package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单表
 * @author guoyh
 * @version 2019年06月04日 15:29:44
 */
public class Menu extends BaseEntity {

    private String parentId;  //父级编号
    private String parentIds;  //所有父级编号
    private String name;  //名称
    private Integer grade;  // 等级
    private Integer sort;  //排序
    private String href;  //链接
    private String target;  //目标
    private String icon;  //图标
    private Boolean show;  //是否在菜单中显示
    private String permission;  // 授权字段

    private Boolean childShow; //展开子菜单
    private List<Menu> children; // 子级菜单
    private Boolean child;

    public Menu() {
        super();
    }

    public Menu(String id){
        super(id);
    }

    @NotNull(message="父级编号不能为空")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Length(min=0, max=2000, message="链接长度必须介于 0 和 2000 之间")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Length(min=0, max=20, message="目标长度必须介于 0 和 20 之间")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Length(min=0, max=100, message="图标长度必须介于 0 和 100 之间")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getShow() {
        return show;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Boolean getChildShow() {
        return childShow;
    }

    public void setChildShow(Boolean childShow) {
        this.childShow = childShow;
    }

    public Boolean getChild() {
        return child;
    }

    public void setChild(Boolean child) {
        this.child = child;
    }

}
