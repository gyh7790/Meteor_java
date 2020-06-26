package com.gyh.system.sys.dto;

import java.io.Serializable;

/**
 * @author gyh
 * @Date 2020/6/24 20:42
 */
public class MailDto implements Serializable {

    private String to;
    private String subject;
    private String content;
    private String filePath;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
