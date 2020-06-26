package com.gyh.system.sys.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.tools.RedisUtil;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dto.MailDto;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.service.UserService;
import com.gyh.system.sys.utils.MailUtils;
import com.mchange.v2.log.MLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author gyh
 * @Date 2020/6/24 20:38
 */
@RestController
@RequestMapping("sys/index")
public class IndexController extends BaseController {


    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @PostMapping("sendMail")
    public R sendMail(@RequestBody MailDto mailDto){

        User user = userService.getUserByEmail(mailDto.getTo());
        if (user != null) { // 用户名
            return R.ok(201,"邮箱已存在！");
        }
        Random random = new Random();
        String code = String.valueOf(random.nextInt(899999) + 100000);
        redisUtil.set(name+mailDto.getTo(),code);
        mailDto.setSubject("获取 " + name + "\t验证码");
        mailDto.setContent("验证码: " + code);
        mailUtils.sendSimpleMail(mailDto);
        return R.ok("发送成功！").put(name+mailDto.getTo(),redisUtil.get(name+mailDto.getTo()));
    }

}
