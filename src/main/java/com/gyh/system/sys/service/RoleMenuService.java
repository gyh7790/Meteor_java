package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.BaseService;
import com.gyh.system.sys.dao.RoleMenuDao;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/25 19:32
 */
@Service
public class RoleMenuService extends BaseService<RoleMenuDao, RoleMenu> {


    /**
     * 根据 角色ID 获取数据
     * @param roleIds 角色ID
     * @return
     */
    public List<String> getMenusByRoleId(List<String> roleIds){
        return dao.getMenusByRoleId(roleIds);
    }

    public int deleteByMenuId(String menuId){
        return dao.deleteByMenuId(menuId);
    }

}
