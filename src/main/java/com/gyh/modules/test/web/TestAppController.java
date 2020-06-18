package com.gyh.modules.test.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.PropertiesUtil;
import com.gyh.modules.test.entity.TestApp;
import com.gyh.modules.test.service.TestAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/11 23:10
 */
@RestController
public class TestAppController extends BaseController {

    @Autowired
    private TestAppService testAppService;


    @GetMapping("getStr")
    public String get(){
        logger.info("任务名称: 参数:  执行定时任务...........");
        return "dsfkjksadfkjsdafhjkldasfkldjasfkldjasl";
    }

    @GetMapping("getList")
    public List<TestApp> getList(){
        return testAppService.findList();
    }

}
