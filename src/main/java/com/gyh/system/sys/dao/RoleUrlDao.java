package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.RoleUrl;

/**
 * @author gyh
 * @Date 2020/6/27 21:11
 */
public interface RoleUrlDao extends CrudDao<RoleUrl> {


    int deleteByUrlId(String urlId);

    int deleteByRoleId(String roleId);
}
