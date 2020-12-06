package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.BaseService;
import com.gyh.common.tools.ListUtils;
import com.gyh.system.sys.dao.MenuDao;
import com.gyh.system.sys.dao.RoleDao;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/14 0:11
 */
@Service
public class RoleService extends BaseService<RoleDao, Role> {

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 查询所有数据列表
     * @return
     */
    public List<Role> findAllList() {
        return dao.findAllList(new Role());
    }

    public List<Role> getRolesByUrlId(String urlId){
        return dao.getRolesByUrlId(urlId);
    }

    @Transactional
    public void setAuthorize(String roleId,List<String> menuIds){
        List<RoleMenu> list = new ArrayList<>();
        roleMenuService.deleteById(roleId);
        menuIds.parallelStream().forEach(e->{
            list.add(new RoleMenu(roleId,e));
        });
        if (ListUtils.isNotEmpty(list)) {
            roleMenuService.insertList(list);
        }
    }

}
