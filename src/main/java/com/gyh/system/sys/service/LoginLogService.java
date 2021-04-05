package com.gyh.system.sys.service;

import com.gyh.system.sys.entity.LoginLog;
import com.gyh.system.sys.entity.OperLog;
import org.springframework.security.core.Authentication;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gyh
 * @Date 2021/4/5 0:18
 */
public interface LoginLogService {

    /**
     * 保存 登入记录
     * @param LoginLog
     * @return
     */
    int save(LoginLog LoginLog);

    /**
     * 保存 登入日志
     * @param request
     * @param response
     * @return
     */
    int saveLoginLog(HttpServletRequest request, HttpServletResponse response);
}
