package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;

import java.util.List;

/**
 * 系统字典
 * @author gyh
 * @Date 2020/7/3 13:17
 */
public class DictType extends BaseEntity {

    private String name;
    private String type;

    private List<DictData> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DictData> getList() {
        return list;
    }

    public void setList(List<DictData> list) {
        this.list = list;
    }
}
