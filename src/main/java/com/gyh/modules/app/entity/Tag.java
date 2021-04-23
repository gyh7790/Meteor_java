package com.gyh.modules.app.entity;

import com.gyh.common.persistence.base.BaseEntity;

/**
 * @author gyh
 * @Date 2021/4/15 14:30
 */
public class Tag extends BaseEntity {

    /**
     * tag 名称
     */
    private String label;

    /**
     * 顺序
     */
    private String sort;

    /**
     * 类型
     */
    private Integer type;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
