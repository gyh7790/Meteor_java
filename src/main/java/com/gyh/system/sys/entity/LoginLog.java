package com.gyh.system.sys.entity;

import com.gyh.common.persistence.base.BaseEntity;

import java.util.Date;

/**
 * 登入 日志
 * @author gyh
 * @Date 2021/4/5 0:07
 */
public class LoginLog extends BaseEntity {

    private String loginName;

    private String loginIp;

    private String loginLocation;

    private String userAgent;

    private String version;

    private String device;

    private String system;

    private Integer status;

    private String operMsg;

    private Date operTime;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperMsg() {
        return operMsg;
    }

    public void setOperMsg(String operMsg) {
        this.operMsg = operMsg;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
