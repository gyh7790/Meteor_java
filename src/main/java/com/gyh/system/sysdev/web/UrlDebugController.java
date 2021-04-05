package com.gyh.system.sysdev.web;

import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.system.sys.entity.DictType;
import com.gyh.system.sysdev.entity.UrlDebug;
import com.gyh.system.sysdev.service.UrlDebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/11/21 12:17
 */
@RestController
@RequestMapping("sysdev/urlDebug")
public class UrlDebugController extends BaseController {

    @Autowired
    private UrlDebugService urlDebugService;

    @GetMapping("{id}")
    public R get(@PathVariable("id") String id){
        return R.ok(urlDebugService.get(id));
    }

    @GetMapping("page")
    public R getPage(UrlDebug urlDebug) {
        setPage();
        List<UrlDebug> page = urlDebugService.findList(urlDebug);
        return R.page(page);
    }


}