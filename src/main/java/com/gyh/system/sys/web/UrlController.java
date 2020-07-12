package com.gyh.system.sys.web;

import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.system.sys.entity.Menu;
import com.gyh.system.sys.entity.Url;
import com.gyh.system.sys.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/27 21:27
 */
@RestController
@RequestMapping(value = "sys/url")
public class UrlController extends BaseController {

    @Autowired
    private UrlService urlService;

    /**
     * 分页查询数据
     * @param url
     * @return
     */
    @GetMapping("page")
    public R page(Url url, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        Page<Url> page = urlService.findPage(new Page<Url>(pageNo, pageSize),url);
        return R.ok("page", page);
    }

    @PostMapping("add")
    public R addUrl(@RequestBody List<Url> list){
        int row = urlService.addUrlAndRoleUrl(list);
        if (row > 0) {
            return R.ok("添加成功");
        } else {
            return R.ok("添加失败");
        }
    }

    @PutMapping("auth")
    public R putAuth(@RequestBody Url url){
        int row = urlService.putAuth(url);
        if (row > 0) {
            return R.ok("修改成功！").put("data",url);
        }
        return R.ok(url);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){
        int row = urlService.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功！");
        }
        return R.error("删除失败！");
    }
}
