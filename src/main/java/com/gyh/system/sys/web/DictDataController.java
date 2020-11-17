package com.gyh.system.sys.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.tools.Assert;
import com.gyh.common.tools.StringUtils;
import com.gyh.common.utils.R;
import com.gyh.system.sys.entity.DictData;
import com.gyh.system.sys.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/7/3 15:25
 */
@RestController
@RequestMapping("sys/dictData")
public class DictDataController extends BaseController {

    @Autowired
    private DictDataService dictDataService;

    @GetMapping(value = "{type}")
    public R get(@PathVariable String type) {
        Assert.isBlank(type, "请输入类型！");
        return R.ok().put("list", dictDataService.getListByType(type));
    }

    @GetMapping("getDictData")
    public R getDictData(@RequestParam(value = "types") List<String> types) {
        Assert.isNull(types, "请输入类型！");
        return R.ok(dictDataService.getDictData(types));
    }


    @PutMapping("default")
    public R setDefault(@RequestBody DictData dictData){
        if (dictData.getDefaults() == 1) {
            dictDataService.setDefaultByType(dictData.getDictType(),0);
            dictDataService.setDefaultById(dictData.getId(),dictData.getDefaults());
        } else {
            dictDataService.setDefaultById(dictData.getId(),dictData.getDefaults());
        }
        return R.ok("更改成功");
    }


    @PostMapping("save")
    public R save(@RequestBody DictData dictData){
        int row = dictDataService.save(dictData);
        if (row > 0) {
            return R.ok("保存成功");
        }
        return R.error("保存失败");
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){
        int row = dictDataService.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功！");
        }
        return R.error("删除失败！");
    }

}
