package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.system.sys.dao.UserDao;
import com.gyh.system.sys.dto.LoginUser;
import com.gyh.system.sys.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author gyh
 * @Date 2020/6/12 18:16
 */
@Service
public class UserService extends CrudService<UserDao,User> implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = dao.getByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new LoginUser(user);
    }

}
