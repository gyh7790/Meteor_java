package com.gyh.system.sys.service.Impl;

import com.gyh.common.persistence.service.BaseService;
import com.gyh.system.sys.dao.OperLogDao;
import com.gyh.system.sys.entity.OperLog;
import com.gyh.system.sys.service.OperLogService;
import org.springframework.stereotype.Service;

/**
 * @author gyh
 * @Date 2021/3/20 2:17
 */
@Service("operLogService")
public class OperLogServiceImpl extends BaseService<OperLogDao, OperLog> implements OperLogService {

    @Override
    public int save(OperLog operLog){
        return super.save(operLog);
    }
}
