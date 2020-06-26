package com.gyh.system.sys.web;

import com.gyh.common.tools.Assert;
import com.gyh.common.utils.R;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.constructor.BaseConstructor;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/12 18:20
 */
@RestController
@RequestMapping(value = "sys/user")
public class UserController extends BaseConstructor {

    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    public R get(@PathVariable String id) {
        return id != null ? R.ok().put("user", userService.get(id)) : R.ok("没有查询到数据。");
    }

    /**
     * 查询数据列表，
     * @param user
     * @return
     */
    @GetMapping("list")
    public R list(User user) {
        List<User> userList = userService.findList(user);
        return R.ok("list",userList);
    }

    @GetMapping("verifyUserName")
    public R getUserByUserName(String userName){
        Assert.isBlank(userName,"请输入用户名");
        User user = userService.getUserByUserName(userName);
        if (user != null) { // 用户名
            return R.ok(201,"用户名已存在！");
        }
        return R.ok();
    }

    @GetMapping("verifyEmail")
    public R getUserByEmail(String email){
        User user = userService.getUserByEmail(email);
        if (user != null) { // 用户名
            return R.ok(201,"邮箱已存在！");
        }
        return R.ok();
    }

    /**
     * 保存数据
     * @param user
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody User user) {
        int row = userService.save(user);
        if (row > 0) {
            return R.ok("成功保存(" + row +")条");
        } else {
            return R.error("保存失败");
        }
    }

    /**
     * 删除 数据
     * @return
     */
    @DeleteMapping(value = "{id}")
    public R deleteById(@PathVariable String id) {
        int row = userService.deleteById(id);
        if (row > 0) {
            return R.ok("成功删除(" + row +")条");
        } else {
            return R.error("删除失败");
        }
    }
}
