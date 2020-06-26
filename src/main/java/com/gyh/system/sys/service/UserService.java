package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.common.utils.R;
import com.gyh.system.sys.dao.UserDao;
import com.gyh.system.sys.dto.LoginUser;
import com.gyh.system.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/12 18:16
 */
@Service
public class UserService extends CrudService<UserDao,User> implements UserDetailsService {


    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = dao.getByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new LoginUser(user);
    }

    /**
     * 根据 用户名获取用户
     * @param loginName 用户名
     * @return
     */
    public User getUserByUserName(String loginName){
        return dao.getByLoginName(loginName);
    }

    /**
     * 根据 用户名获取用户
     * @param email 邮箱
     * @return
     */
    public User getUserByEmail(String email){
        return dao.getUserByEmail(email);
    }


}
