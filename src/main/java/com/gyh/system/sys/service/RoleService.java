package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.system.sys.dao.MenuDao;
import com.gyh.system.sys.dao.RoleDao;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/14 0:11
 */
@Service
public class RoleService extends CrudService<RoleDao, Role> {

    /**
     * 查询所有数据列表
     * @return
     */
    public List<Role> findAllList() {
        return dao.findAllList(new Role());
    }




}
