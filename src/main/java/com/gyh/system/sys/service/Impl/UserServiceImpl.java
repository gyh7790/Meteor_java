package com.gyh.system.sys.service.Impl;

import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.service.BaseService;
import com.gyh.common.tools.ListUtils;
import com.gyh.system.sys.dao.UserDao;
import com.gyh.system.sys.dao.UserRoleDao;
import com.gyh.system.sys.dto.LoginUser;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.entity.UserRole;
import com.gyh.system.sys.service.MenuService;
import com.gyh.system.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gyh
 * @Date 2020/11/17 15:27
 */
@Service("UserService")
public class UserServiceImpl extends BaseService<UserDao,User> implements UserDetailsService, UserService  {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRoleDao userRoleDao;

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
    @Override
    public User getUserByUserName(String loginName){
        return dao.getByLoginName(loginName);
    }

    /**
     * 根据 用户名获取用户
     * @param email 邮箱
     * @return
     */
    @Override
    public User getUserByEmail(String email){
        return dao.getUserByEmail(email);
    }


    @Override
    @Transactional
    public int saveUserAndRole(User user){
        int row = save(user);
        if (row > 0) {
            userRoleDao.deleteByUserId(user.getId());
            if (ListUtils.isNotEmpty(user.getRoles())) {
                List<UserRole> userRoleList = new ArrayList<>();
                user.getRoles().parallelStream().forEach(e->{
                    userRoleList.add(new UserRole(user.getId(),e.getId()));
                });
                userRoleDao.insertUserAndRole(userRoleList);
            }
        }
        return row;
    }

}
