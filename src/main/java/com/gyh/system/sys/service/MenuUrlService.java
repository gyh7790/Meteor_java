package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.common.tools.Global;
import com.gyh.system.sys.dao.MenuUrlDao;
import com.gyh.system.sys.dao.RoleMenuDao;
import com.gyh.system.sys.entity.MenuUrl;
import com.gyh.system.sys.entity.RoleMenu;
import com.gyh.system.sys.entity.RoleUrl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2020/6/26 19:28
 */
@Service
public class MenuUrlService extends CrudService<MenuUrlDao, MenuUrl> {

    @Autowired
    private RoleUrlService roleUrlService;

    public int addUrlAndRoleUrl(List<MenuUrl> list){
        int row = 0;
        for (MenuUrl menuUrl : list) {
            if (StringUtils.isEmpty(menuUrl.getId())) {
                row += insert(menuUrl);
            } else {
                row += update(menuUrl);
            }
        }

        List<RoleUrl> saveList = new ArrayList<>();
        List<String> roles = Global.getRoleIds();
        for (String role : roles) {
            List<RoleUrl> roleUrls = list.stream().map(e->new RoleUrl(role,e.getId())).collect(Collectors.toList());
            saveList.addAll(roleUrls);
        }

        roleUrlService.insertList(saveList);
        return row;
    }

}
