package com.gyh.system.sys.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.system.sys.entity.MenuUrl;
import com.gyh.system.sys.service.MenuUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/27 21:27
 */
@RestController
@RequestMapping(value = "sys/url")
public class UrlController extends BaseController {

    @Autowired
    private MenuUrlService menuUrlService;

    @PostMapping("addUrl")
    public R addUrl(@RequestBody List<MenuUrl> list){
        int row = menuUrlService.addUrlAndRoleUrl(list);
        if (row > 0) {
            return R.ok("添加成功");
        } else {
            return R.ok("添加失败");
        }
    }
}
