package com.gyh.system.sys.service;

import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.service.BaseService;
import com.gyh.common.tools.ListUtils;
import com.gyh.system.sys.dao.UserDao;
import com.gyh.system.sys.dao.UserRoleDao;
import com.gyh.system.sys.dto.LoginUser;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.entity.UserRole;
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
 * @Date 2020/6/12 18:16
 */
public interface UserService {


    User get(String id);

    UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException;

    /**
     * 根据 用户名获取用户
     * @param loginName 用户名
     * @return
     */
    User getUserByUserName(String loginName);

    /**
     * 根据 用户名获取用户
     * @param email 邮箱
     * @return
     */
    public User getUserByEmail(String email);


    public int saveUserAndRole(User user);

    List<User> findList(User user);

    Page<User> findPage(Page<User> userPage, User user);

    int deleteById(String id);

    int insert(User user);
}
