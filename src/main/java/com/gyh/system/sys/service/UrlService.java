package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.common.tools.Global;
import com.gyh.common.tools.ListUtils;
import com.gyh.common.tools.StringUtils;
import com.gyh.system.sys.dao.UrlDao;
import com.gyh.system.sys.dto.UrlDto;
import com.gyh.system.sys.entity.RoleUrl;
import com.gyh.system.sys.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



    public List<UrlDto> getAuthIgnoreUri(){
        return dao.getAuthIgnoreUri();
    }

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

    @Transactional
    public int saveUrlAndMenuRole(Url url,List<String> roleIds){
        int row = super.save(url);
        if (row > 0) {
            roleUrlService.deleteByUrlId(url.getId());
            List<RoleUrl> roleUrlList = new ArrayList<>();
            if (ListUtils.isNotEmpty(roleIds)) {
                roleIds.parallelStream().forEach(e->{
                    roleUrlList.add(new RoleUrl(e,url.getId()));
                });
            }
            if (ListUtils.isNotEmpty(roleUrlList)) {
                roleUrlService.insertList(roleUrlList);
            }
        }
        return row;
    }

    public int putAuth(Url url){
        url.preUpdate();
        return dao.putAuth(url);
    }

    public String getMaxCoide() {
        String maxCode = dao.getMaxCode();
        char prefix = maxCode.charAt(0);
        String suffix = StringUtils.right(maxCode,3);
        String nextSuffix = StringUtils.add(suffix,"1");
        if (StringUtils.length(nextSuffix) > 3) {
            return (char)(prefix + 1) + "001";
        } else {
            return prefix + StringUtils.leftPad(nextSuffix,3,"0");
        }
    }
}
