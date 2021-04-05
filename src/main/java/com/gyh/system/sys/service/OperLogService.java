package com.gyh.system.sys.service;

import com.gyh.system.sys.entity.OperLog;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/3/20 2:16
 */
public interface OperLogService {

    List<OperLog> findList(OperLog operLog);

    int save(OperLog operLog);
}
