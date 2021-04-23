package com.gyh.modules.app.entity;

import com.gyh.common.persistence.base.BaseEntity;

import java.util.Date;

/**
 * @author gyh
 * @Date 2021/4/15 14:30
 */
public class Video extends BaseEntity {

    /**
     * 编码
     */
    private String code;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String img;

    /**
     * 区域
     */
    private String area;

    /**
     * 类型
     */
    private Integer type;

    /**
     *
     */
    private Date launchTime;

    /**
     * 简介
     */
    private String intro;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
