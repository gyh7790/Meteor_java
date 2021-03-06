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

    @Override
    public String getId() {
        return id;
    }

    @Override
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

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "TestApp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", createDate=" + createDate +
                ", del=" + del +
                '}';
    }
}
