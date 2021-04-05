package com.gyh.system.sys.service.Impl;

import com.gyh.common.constant.Constant;
import com.gyh.common.constant.HttpStatus;
import com.gyh.common.persistence.service.BaseService;
import com.gyh.common.tools.Assert;
import com.gyh.common.tools.DateUtils;
import com.gyh.common.tools.JsonUtils;
import com.gyh.common.utils.ServletUtils;
import com.gyh.system.sys.dao.LoginLogDao;
import com.gyh.system.sys.dao.OperLogDao;
import com.gyh.system.sys.entity.LoginLog;
import com.gyh.system.sys.entity.OperLog;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.service.LoginLogService;
import com.gyh.system.sys.utils.IpUtils;
import com.gyh.system.sys.utils.UserUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gyh
 * @Date 2021/4/5 0:18
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends BaseService<LoginLogDao, LoginLog> implements LoginLogService {

    @Override
    public int save(LoginLog loginLog) {
        return super.save(loginLog);
    }

    @Override
    public int saveLoginLog(HttpServletRequest req, HttpServletResponse resp) {
        LoginLog loginLog = new LoginLog();
        loginLog.setStatus(resp.getStatus());
        loginLog.setOperMsg(HttpStatus.SC_OK == resp.getStatus() ? "登录成功" : "登入失败");
        loginLog.setLoginIp(IpUtils.getIpAddr(req));
        loginLog.setLoginLocation(IpUtils.getAddrByIp(loginLog.getLoginIp()));
        loginLog.setLoginName(req.getParameter("username"));
        loginLog.setVersion(req.getHeader("version"));
        loginLog.setDevice(req.getParameter("device"));
        loginLog.setUserAgent(req.getHeader("User-Agent"));
        loginLog.setOperTime(DateUtils.now());
        return save(loginLog);
    }


}
