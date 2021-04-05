package com.gyh.system.job.entity;

import com.gyh.common.persistence.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 系统定时任务
 * @author guoyh
 * @version 2019年05月27日 21:53:39
 */
public class ScheduleJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String jobName;  //任务名称
    private String beanName;  //spring bean名称
    private String methodName;  //方法名
    private String params;  //参数
    private String cronExpression;  //cron表达式
    private Integer status;  //任务状态  0：正常  1：暂停


    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";


    public ScheduleJob() {
        super();
    }

    public ScheduleJob(String jobName) {
        this.jobName = jobName;
    }

    @Length(min = 0, max = 64, message = "任务名称长度必须介于 0 和 64 之间")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Length(min = 0, max = 200, message = "spring bean名称长度必须介于 0 和 200 之间")
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Length(min = 0, max = 100, message = "方法名长度必须介于 0 和 100 之间")
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Length(min = 0, max = 2000, message = "参数长度必须介于 0 和 2000 之间")
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Length(min = 0, max = 100, message = "cron表达式长度必须介于 0 和 100 之间")
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}