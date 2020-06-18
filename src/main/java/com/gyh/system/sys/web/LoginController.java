package com.gyh.system.sys.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.tools.Assert;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dto.LoginUser;
import org.springframework.web.bind.annotation.*;

/**
 * @author gyh
 * @Date 2020/6/16 20:41
 */
@RestController
@RequestMapping("sys/login")
public class LoginController extends BaseController {

    @PostMapping()
    public R login(@RequestBody LoginUser user){
        Assert.isBlank(user.getUsername(), "用户名不能为空");
        Assert.isBlank(user.getPassword(), "密码不能为空");
        logger.debug(user.toString());
        return R.ok();
    }

    @GetMapping("app")
    public R loginee(){
        logger.debug("1111111111111111111111111111111");
        return R.ok("654687987465987*/98465132/7894561");
    }
}
