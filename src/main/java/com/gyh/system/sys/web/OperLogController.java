package com.gyh.system.sys.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.system.sys.entity.OperLog;
import com.gyh.system.sys.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/5 12:54
 */
@RestController
@RequestMapping("sys/operLog")
public class OperLogController extends BaseController {

    @Autowired
    private OperLogService operLogService;

    @GetMapping("page")
    public R getPage(OperLog operLog){
        setPage("a.oper_time desc");
        List<OperLog> result = operLogService.findList(operLog);
        return R.page(result);
    }

}
