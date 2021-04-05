package com.gyh.common.persistence.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gyh.common.persistence.model.Page;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * @author gyh
 * @Date 2020/6/12 17:04
 */
public abstract class BaseRootEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;    // 编号

    public BaseRootEntity() {}

    public BaseRootEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 插入之前执行
     */
    public abstract void preInsert();

    /**
     * 修改执行执行
     */
    public abstract void preUpdate();


    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final int DEL_NORMAL = 0;
    public static final int DEL_DELETE = 1;
    public static final int DEL_AUDIT = 2;

}
