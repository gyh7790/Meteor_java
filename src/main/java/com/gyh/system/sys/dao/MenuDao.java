package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.dto.MenuDto;
import com.gyh.system.sys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/13 23:17
 */
public interface MenuDao extends CrudDao<Menu> {

    /**
     * 根据 角色ID获取 菜单
     * @param roles 角色 集合
     * @return
     */
    List<MenuDto> getListByRoles(@Param("roles") List<String> roles);

    /**
     * 根据 角色ID获取 菜单
     * @param roles 角色 集合
     * @return
     */
    List<MenuDto> getListUrlByRoles(@Param("roles") List<String> roles);
}
