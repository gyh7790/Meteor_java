package com.gyh.system.sys.web;

import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.tools.Global;
import com.gyh.common.tools.ListUtils;
import com.gyh.common.tools.StringUtils;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dto.MenuDto;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.service.MenuService;
import com.gyh.system.sys.service.RoleMenuService;
import com.gyh.system.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2020/6/13 23:40
 */
@RestController
@RequestMapping("sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping(value = "{id}")
    public R get(@PathVariable String id) {
        return id != null ? R.ok().put("menu", menuService.get(id)) : R.ok("没有查询到数据。");
    }

    /**
     * 查询数据列表，
     * @param menu
     * @return
     */
    @GetMapping("list")
    public R list(Menu menu) {
        List<Menu> menuList = menuService.findList(menu);
        return R.ok("list",menuList);
    }

    @GetMapping("getTreeByRoleId")
    public R getTreeByRoleId(){
        List<MenuDto> result = menuService.getListByRoles(UserUtils.getRoleIds());
        return R.ok(result);
    }

    @GetMapping("getMenuIdByRoleId")
    public R getMenuIdByRoleId(String roleId){
        List<String> reuslt = roleMenuService.getMenusByRoleId(Arrays.asList(roleId));
        return R.ok(reuslt);
    }


    /**
     * 查询数据列表
     * @return
     */
    @GetMapping("nav")
    public R navigation() {
        List<Menu> menuList = menuService.findAllList();
        Menu menu = new Menu();
        menu.setId("1");
        List<Menu> resultList = menuService.getNavTree(menuList,menu);
        return R.ok("list",resultList);
    }

    /**
     * 查询数据列表
     * @return
     */
    @GetMapping("getNav")
    public R getNav() {
        List<String> roleIds = Global.getRoleIds();
        if (ListUtils.isEmpty(roleIds)) {
            return R.error("未能获取角色");
        }
        // 角色 非空时 获取菜单
        List<MenuDto> menuList = menuService.getListByRoles(roleIds);
        return R.ok("list",menuList);
    }

    /**
     * 查询数据列表
     * @return
     */
    @GetMapping("getNavUrl")
    public R getNavUrl() {
        List<String> roleIds = Global.getRoleIds();
        if (ListUtils.isEmpty(roleIds)) {
            return R.error("未能获取角色");
        }
        // 角色 非空时 获取菜单
        List<MenuDto> menuList = menuService.getListUrlByRoles(roleIds);
        return R.ok("list",menuList);
    }


    /**
     * 查询数据列表
     * @return
     */
    @GetMapping("navList")
    public R navigationList() {
        List<Menu> resultList = new ArrayList<>();
        List<Menu> menuList = menuService.findAllList();
        Menu menu = new Menu();
        menu.setId("1");
        menuService.getNavTreeList(menuList,menu,resultList);
        return R.ok("list",resultList);
    }


    /**
     * 分页查询数据
     * @param menu
     * @return
     */
    @GetMapping("page")
    public R page(Menu menu, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        Page<Menu> page = menuService.findPage(new Page<Menu>(pageNo, pageSize),menu);
        return R.ok("page", page);
    }


    /**
     * 查询全部数据
     * @return
     */
    @GetMapping("listAll")
    public R list() {
        List<Menu> menuList = menuService.findAllList(new Menu());
        return R.ok("list",menuList);
    }

    /**
     * 新增菜单
     * @param menu 菜单信息
     * @return
     */
    @PostMapping("add")
    public R add(@RequestBody Menu menu) {
        // 添加 默认的父级ID
        if (StringUtils.isEmpty(menu.getParentId())) menu.setParentId("1");
        if (menu.getSort() == null) menu.setSort(1);

        // 添加 父级菜单处理
        Menu parentMenu = menuService.get(menu.getParentId());
        if (parentMenu != null) {
            String ids = (parentMenu.getGrade() > 0 ? parentMenu.getParentIds()+"," : "" ) + parentMenu.getId();
            menu.setParentIds(ids);
            menu.setGrade(parentMenu.getGrade()+1);
        }

        int row = menuService.insertAndRoleMenu(menu);
        if (row > 0) {
            return R.ok("添加成功").put("menu", menu);
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改菜单
     * @param menu 菜单信息
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Menu menu) {
        if (menu.getSort() == null) menu.setSort(1);

        int row = menuService.update(menu);
        if (row > 0) {
            return R.ok("修改成功").put("menu", menu);
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 保存数据
     * @param menu
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody Menu menu) {
        int row = menuService.save(menu);
        if (row > 0) {
            return R.ok("成功保存(" + row +")条").put("menu", menu);
        } else {
            return R.error("保存失败");
        }
    }


    /**
     * 删除 数据
     * @return
     */
    @DeleteMapping(value = "{id}")
    public R delete(@PathVariable String id) {
        int row = menuService.delete(id);
        if (row > 0) {
            return R.ok("成功删除(" + row +")条");
        } else {
            return R.error("删除失败");
        }
    }

}
