package com.gyh.system.sys.service;

import com.gyh.common.constant.Constant.*;
import com.gyh.common.persistence.service.CrudService;
import com.gyh.common.tools.Global;
import com.gyh.system.sys.dao.MenuDao;
import com.gyh.system.sys.dto.MenuDto;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.RoleMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表 Service
 * @author guoyh
 * @version 2019年06月04日 15:29:44
 */
@Service
public class MenuService extends CrudService<MenuDao, Menu> {

    @Autowired
    private RoleMenuService roleMenuService;

    @Cacheable(cacheNames = "MenuService",key = "'MenuService.findAllList'")
    public List<Menu> findAllList(){
        return dao.findAllList(new Menu());
    }


    public Object navigation(){
        return null;
    }

//    public String getPermission(String id){
//        StringBuilder sb = new StringBuilder();
//        getPermission();
//        return getPermission();
//    }

    /**
     * 获取 授权字段
     * @return
     */
    public String getPermission(String id){
        String perm = "";
        Menu menu = dao.get(id);
        if (menu.getGrade() > 0) { // 不是顶级
            perm = getPermission(menu.getParentId());
        }
        return (StringUtils.isNotEmpty(perm) ? perm + ":" : "") + menu.getTarget();
    }


    /**
     * 保存菜单
     * @param menu
     * @return
     */
    @Override
    public int save(Menu menu){
        return super.save(menu);
    }



    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @return
     */
    @CacheEvict(cacheNames = "MenuService",key = "'MenuService.findAllList'")
    public Integer delete(String id) {
        return dao.deleteById(id);
    }


    /**
     * 添加 菜单
     * @param menu
     * @return
     */
    public int insertAndRoleMenu(Menu menu){
        List<String> roleIds = Global.getRoleIds();
        int rows = insert(menu);
        if (rows > 0) {
            roleMenuService.deleteByMenuId(menu.getId());
            List<RoleMenu> list = new ArrayList<>();
            RoleMenu roleMenu = null;
            for (String roleId : roleIds) {
                roleMenu = new RoleMenu(roleId,menu.getId());
                list.add(roleMenu);
                for (String menuId : StringUtils.split(menu.getParentIds(), SymEnum.COMMA.getValue())) {
                    roleMenu = new RoleMenu(roleId,menuId);
                    list.add(roleMenu);
                }
            }
            roleMenuService.insertList(list);
        }
        return rows;
    }

    /**
     * 根据 角色ID获取 菜单
     * @param roles 角色 集合
     * @return
     */
    public List<MenuDto> getListByRoles(List<String> roles){
        List<MenuDto> menuResult = dao.getListByRoles(roles);
        MenuDto menu = new MenuDto();
        menu.setId("1");
        return getNavTree(menuResult,menu);
    }

    /**
     * 根据 角色ID获取 菜单(带有url)
     * @param roles 角色 集合
     * @return
     */
    public List<MenuDto> getListUrlByRoles(List<String> roles){
        List<MenuDto> menuResult = dao.getListUrlByRoles(roles);
        MenuDto menu = new MenuDto();
        menu.setId("1");
        return getNavTree(menuResult,menu);
    }

    public List<MenuDto> getNavTree(List<MenuDto> menuList,MenuDto parentMenu){
        List<MenuDto> menuResult = new ArrayList<>();
        for (MenuDto childMenu : menuList) {
            if (StringUtils.equals(parentMenu.getId(),childMenu.getParentId())) {
                childMenu.setChildren(getNavTree(menuList,childMenu));
                if (childMenu.getChildren() != null && !childMenu.getChildren().isEmpty()) {
                    childMenu.setChildShow(false);
                }
                menuResult.add(childMenu);
            }
        }
        return menuResult;
    }

    public List<Menu> getNavTree(List<Menu> menuList,Menu parentMenu){
        List<Menu> menuResult = new ArrayList<>();
        for (Menu childMenu : menuList) {
            if (StringUtils.equals(parentMenu.getId(),childMenu.getParentId())) {
                childMenu.setChildren(getNavTree(menuList,childMenu));
                if (childMenu.getChildren() != null && !childMenu.getChildren().isEmpty()) {
                    childMenu.setChildShow(false);
                }
                menuResult.add(childMenu);
            }
        }
        return menuResult;
    }

    public int getNavTreeList(List<Menu> menuList,Menu parentMenu,List<Menu> resultList){
        List<Menu> menuResult = new ArrayList<>();
        int count = 0;
        for (Menu childMenu : menuList) {
            if (childMenu.getParentId() == parentMenu.getId()) {
                count++;
                childMenu.setShow(false);
                resultList.add(childMenu);
                int result = getNavTreeList(menuList,childMenu,resultList);
                if (result > 0) {
                    childMenu.setChildShow(false);
                    childMenu.setChild(true);
                }
            }
        }
        return count;
    }


}