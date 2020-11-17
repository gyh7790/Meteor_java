package com.gyh.system.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author gyh
 * @Date 2020/11/13 14:35
 */
public class DictDto {

    private String label;
    private String value;

    private String dictType;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }
}
