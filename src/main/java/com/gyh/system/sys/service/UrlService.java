package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.common.tools.Global;
import com.gyh.system.sys.dao.UrlDao;
import com.gyh.system.sys.entity.RoleUrl;
import com.gyh.system.sys.entity.Url;
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
public class UrlService extends CrudService<UrlDao, Url> {

    @Autowired
    private RoleUrlService roleUrlService;

    @Autowired
    private MenuService menuService;



    public int addUrlAndRoleUrl(List<Url> list){
        int row = 0;
        for (Url url : list) {
            url.setPermission(menuService.getPermission(url.getMenuId()));
            if (StringUtils.isEmpty(url.getId())) {
                row += insert(url);
            } else {
                row += update(url);
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

    public int putAuth(Url url){
        url.preUpdate();
        return dao.putAuth(url);
    }

}
