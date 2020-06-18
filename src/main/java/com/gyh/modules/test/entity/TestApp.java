package com.gyh.modules.test.entity;

import com.gyh.common.persistence.base.BaseEntity;

import java.util.Date;

/**
 * @author gyh
 * @Date 2020/6/11 23:01
 */
public class TestApp extends BaseEntity {

    private String id;
    private String name;
    private int age;
    private Date createDate;
    private int del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }
}
