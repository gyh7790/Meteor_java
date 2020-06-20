package com.gyh.system.sys.web;

import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.tools.ListUtils;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dto.MenuDto;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.service.MenuService;
import com.gyh.system.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.constructor.BaseConstructor;

import java.security.Principal;
import java.util.ArrayList;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roleId = null;
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            roleId = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        }
        if (ListUtils.isEmpty(roleId)) {
            return R.error("未能获取角色");
        }
        // 角色 非空时 获取菜单
        List<MenuDto> menuList = menuService.getListByRoles(roleId);
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
    public R delete(@PathVariable Long id) {
        int row = menuService.delete(id);
        if (row > 0) {
            return R.ok("成功删除(" + row +")条");
        } else {
            return R.error("删除失败");
        }
    }

}
