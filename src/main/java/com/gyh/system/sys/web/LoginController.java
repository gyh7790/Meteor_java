package com.gyh.system.sys.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.tools.Assert;
import com.gyh.common.tools.RedisUtil;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dto.LoginUser;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author gyh
 * @Date 2020/6/16 20:41
 */
@RestController
@RequestMapping("sys/login")
public class LoginController extends BaseController {

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping()
    public R login(@RequestBody LoginUser user){
        Assert.isBlank(user.getUsername(), "用户名不能为空");
        Assert.isBlank(user.getPassword(), "密码不能为空");
        logger.debug(user.toString());
        return R.ok();
    }


    /**
     * 保存数据
     * @param user
     * @return
     */
    @PostMapping("register")
    public R save(@RequestBody LoginUser user) {
        Assert.isBlank(user.getUsername(),"请输入用户名！");
        Assert.isBlank(user.getPassword(),"请输入密码！");
        Assert.isBlank(user.getEmail(),"请输入邮箱！");
        Assert.isBlank(user.getCaptcha(),"请输入验证码！");

        String redisCaptcha = redisUtil.get(name+user.getEmail()).toString();
        if (!user.getCaptcha().equals(redisCaptcha)) {
            return R.error("验证码输入有误！");
        }
        User user1 = new User();
        user1.setLoginName(user.getUsername());
        user1.setName(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        int row = userService.insert(user1);
        if (row > 0) {
            return R.ok("注册成功！");
        } else {
            return R.error("注册成功！");
        }
    }
}
