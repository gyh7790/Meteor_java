package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/25 19:31
 */
public interface RoleMenuDao extends CrudDao<RoleMenu> {

    /**
     * 根据 角色ID 获取数据
     *
     * @param roleIds 角色ID
     * @return
     */
    List<String> getMenusByRoleId(@Param("roleIds") List<String> roleIds);

    int deleteByMenuId(@Param("menuId") String menuId);
}
