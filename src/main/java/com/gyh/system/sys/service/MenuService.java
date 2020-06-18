package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.system.sys.dao.MenuDao;
import com.gyh.system.sys.entity.Menu;
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


    public List<Menu> findAllList(){
        return dao.findAllList(new Menu());
    }



    public Object navigation(){
        return null;
    }

    /**
     * 保存菜单
     * @param menu
     * @return
     */
    public int save(Menu menu){
        //清空缓存
//        UserUtils.cleanMenuList();
        return super.save(menu);
    }



    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @return
     */
    public Integer delete(Long id) {
        int row = 0;
//        List<Menu> list = UserUtils.getMenuList();
//        for (Menu menu : list) {
//            String[] ids = StringUtils.split(menu.getParentIds(),",");
//            if (CollectUtils.isContains(ids,id+"")) {
//                row = row + super.delete(menu.getId());
//            }
//        }
//        row = row + super.delete(id);
//        //清空缓存
//        UserUtils.cleanMenuList();
        return row;
    }

    public List<Menu> getNavTree(List<Menu> menuList,Menu parentMenu){
        List<Menu> menuResult = new ArrayList<>();
        for (Menu childMenu : menuList) {
            if (childMenu.getParentId() == parentMenu.getId()) {
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