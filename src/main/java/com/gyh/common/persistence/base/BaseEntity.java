package com.gyh.common.persistence.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gyh.common.utils.IdGen;
import com.gyh.system.sys.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/11 23:38
 */
public abstract class BaseEntity extends BaseRootEntity {

    /**
     * 备注信息
     */
    protected String remarks;
    /**
     * 创建者
     */
    protected User createBy;
    /**
     * 创建时间
     */
    protected Date createDate;
    /**
     * 修改者
     */
    protected User updateBy;
    /**
     * 修改时间
     */
    protected Date updateDate;
    /**
     * 删除标记（0：正常；1：删除；）
     */
    protected Integer del;

    /**
     * 模糊查询关键字
     */
    protected String keyword;

    protected Integer param;

    protected List<String> strList;

    protected List<Integer> intList;

    protected Date startDate;
    protected Date endDate;

    public BaseEntity() {
        super();
        this.del= DEL_NORMAL;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonIgnore
    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createTime) {
        this.createDate = createTime;
    }

    @JsonIgnore
    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateTime) {
        this.updateDate = updateTime;
    }

    @JsonIgnore
    public int getDel() {
        return del == null ? 0 : del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public BaseEntity(String id) {
        super(id);
    }

    @JsonIgnore
    public String getKeyWord() {
        return keyword;
    }

    public void setKeyWord(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public List<Integer> getIntList() {
        return intList;
    }

    public void setIntList(List<Integer> intList) {
        this.intList = intList;
    }

    @JsonIgnore
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonIgnore
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // 创建者 和 修改者有待完善

    @Override
    public void preInsert() {
        setId(IdGen.uuid());
        User user = new User();
        user.setId("1");
        this.createBy = user;
        this.updateBy = user;

        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    @Override
    public void preUpdate() {
        User user = new User();
        user.setId("1");
        this.createBy = user;
        this.updateBy = user;
        this.updateDate = new Date();
    }


}
