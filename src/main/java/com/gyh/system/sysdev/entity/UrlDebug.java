package com.gyh.system.sysdev.entity;

import com.gyh.common.persistence.base.BaseEntity;
import com.gyh.system.sys.entity.DictData;

/**
 * 接口 调试
 * @author gyh
 * @Date 2020/11/21 12:10
 */
public class UrlDebug extends BaseEntity<UrlDebug> {

    private String name;
    private String urlId;
    private String url;
    private String method;
    private String desc;
    private String requestHeaders;
    private String parameters;
    private String body;
    private String bodyDesc;

    private String responseHeaders;
    private String response;
    private String responseDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyDesc() {
        return bodyDesc;
    }

    public void setBodyDesc(String bodyDesc) {
        this.bodyDesc = bodyDesc;
    }
}
