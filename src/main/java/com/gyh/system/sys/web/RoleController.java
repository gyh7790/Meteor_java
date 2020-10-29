package com.gyh.system.sys.web;

import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dto.RoleDto;
import com.gyh.system.sys.dto.UserDto;
import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/14 0:13
 */
@RestController
@RequestMapping("sys/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "{id}")
    public R get(@PathVariable String id) {
        return id != null ? R.ok().put("role", roleService.get(id)) : R.ok("没有查询到数据。");
    }

    /**
     * 查询数据列表，
     * @param role
     * @return
     */
    @GetMapping("list")
    public R list(Role role) {
        List<Role> roleList = roleService.findList(role);
        return R.ok("list",roleList);
    }

    /**
     * 分页查询数据
     * @param role
     * @return
     */
    @GetMapping("page")
    public R page(Role role, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        Page<Role> page = roleService.findPage(new Page<Role>(pageNo, pageSize),role);
        return R.ok("page", page);
    }

    /**
     * 根据
     * @return
     */
    @GetMapping("getRolesByUrlId")
    public R getRolesByUrlId(String urlId){
        return R.ok(roleService.getRolesByUrlId(urlId));
    }

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping("listAll")
    public R list() {
        List<Role> roleList = roleService.findAllList();
        return R.ok("list",roleList);
    }

    /**
     * 保存数据
     * @param role
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody Role role) {
        int row = roleService.save(role);
        if (row > 0) {
            return R.ok("成功保存(" + row +")条");
        } else {
            return R.error("保存失败");
        }
    }

    @PostMapping(value = "setAuthorize")
    public R setAuthorize(@RequestBody RoleDto dto){
        roleService.setAuthorize(dto.getId(),dto.getMenuIds());
        return R.ok("授权成功");
    }

    /**
     * 删除 数据
     * @return
     */
    @DeleteMapping(value = "{id}")
    public R delete(@PathVariable String id) {
        int row = roleService.deleteById(id);
        if (row > 0) {
            return R.ok("成功删除(" + row +")条");
        } else {
            return R.error("删除失败");
        }
    }

}
