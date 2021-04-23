package com.gyh.modules.app.entity;

import com.gyh.common.persistence.base.BaseEntity;

/**
 * @author gyh
 * @Date 2021/4/15 14:31
 */
public class VideoItem extends BaseEntity {

    private String  videoId;

    private String  title;

    private String  label;

    private Integer on;

    private String  url;

    private String  img;

    private Integer type;

    private Integer prefixTime;

    private Integer suffixTime;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getOn() {
        return on;
    }

    public void setOn(Integer on) {
        this.on = on;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrefixTime() {
        return prefixTime;
    }

    public void setPrefixTime(Integer prefixTime) {
        this.prefixTime = prefixTime;
    }

    public Integer getSuffixTime() {
        return suffixTime;
    }

    public void setSuffixTime(Integer suffixTime) {
        this.suffixTime = suffixTime;
    }
}
