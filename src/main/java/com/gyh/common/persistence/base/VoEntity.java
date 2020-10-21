package com.gyh.common.persistence.base;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author gyh
 * @Date 2020/10/20 10:07
 */
public class VoEntity<T> extends BaseEntity<T>{

    // 模糊查询关键字
    protected String keyWord;
    // 开始日期
    protected LocalDate startDate;
    // 结束日期
    protected LocalDate endDate;

    // 开始日期
    protected LocalDateTime startTime;
    // 结束日期
    protected LocalDateTime endTime;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
