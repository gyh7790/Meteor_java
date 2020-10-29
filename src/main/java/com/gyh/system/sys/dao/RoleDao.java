package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.Role;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/14 0:11
 */
public interface RoleDao extends CrudDao<Role> {

    /**
     * 根据 url 获取 url需要的角色
     * @param urlId
     * @return
     */
    List<Role> getRolesByUrlId(String urlId);
}
