package com.gyh.system.sys.web;

import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.tools.StringUtils;
import com.gyh.common.utils.R;
import com.gyh.system.sys.entity.DictType;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/7/3 15:26
 */
@RestController
@RequestMapping("sys/dictType")
public class DictTypeController extends BaseController {


    @Autowired
    private DictTypeService dictTypeService;

    @GetMapping(value = "{id}")
    public R get(@PathVariable String id) {
        return id != null ? R.ok().put("menu", dictTypeService.get(id)) : R.ok("没有查询到数据。");
    }

    /**
     * 查询数据列表，
     * @param dictType
     * @return
     */
    @GetMapping("list")
    public R list(DictType dictType) {
        List<DictType> menuList = dictTypeService.findList(dictType);
        return R.ok("list",menuList);
    }

    /**
     * 分页查询数据
     * @return
     */
    @GetMapping("page")
    public R page(DictType dictType, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        Page<DictType> page = dictTypeService.findPage(new Page<DictType>(pageNo, pageSize),dictType);
        return R.ok("page", page);
    }

    /**
     * 新增菜单
     * @param dictType 字典信息
     * @return
     */
    @PostMapping("add")
    public R add(@RequestBody DictType dictType) {
        // 添加 默认的父级ID
        int row = dictTypeService.insert(dictType);
        if (row > 0) {
            return R.ok("添加成功").put("menu", dictType);
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改菜单
     * @param dictType 菜单信息
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody DictType dictType) {
        int row = dictTypeService.update(dictType);
        if (row > 0) {
            return R.ok("修改成功").put("menu", dictType);
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 保存数据
     * @param dictType
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody DictType dictType) {
        int row = dictTypeService.save(dictType);
        if (row > 0) {
            return R.ok("成功保存(" + row +")条").put("menu", dictType);
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
        int row = dictTypeService.deleteById(id);
        if (row > 0) {
            return R.ok("成功删除(" + row +")条");
        } else {
            return R.error("删除失败");
        }
    }

}
