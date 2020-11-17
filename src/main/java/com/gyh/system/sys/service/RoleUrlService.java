package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.system.sys.dao.RoleDao;
import com.gyh.system.sys.dao.RoleUrlDao;
import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.entity.RoleUrl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/27 21:11
 */
@Service
public class RoleUrlService extends CrudService<RoleUrlDao, RoleUrl> {

    @Override
    public int insertList(List<RoleUrl> list){
        return dao.insertList(list);
    }

    public int deleteByUrlId(String urlId){
        return dao.deleteByUrlId(urlId);
    }

    public int deleteByRoleId(String roleId){
        return dao.deleteByRoleId(roleId);
    }
}
