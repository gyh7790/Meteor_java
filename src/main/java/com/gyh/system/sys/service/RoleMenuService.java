package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.system.sys.dao.RoleMenuDao;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/25 19:32
 */
@Service
public class RoleMenuService extends CrudService<RoleMenuDao, RoleMenu> {

    @Autowired
    private MenuService menuService;


}
