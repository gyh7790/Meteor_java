package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;

/**
 * 系统字典
 * @author gyh
 * @Date 2020/7/3 13:17
 */
public class DictData extends BaseEntity<DictData> {

    private String value;
    private String label;
    private String dictType;
    private Integer sort;
    private Integer defaults;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDefaults() {
        return defaults;
    }

    public void setDefaults(Integer defaults) {
        this.defaults = defaults;
    }
}
