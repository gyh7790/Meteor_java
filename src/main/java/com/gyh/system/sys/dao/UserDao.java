package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/12 18:14
 */
public interface UserDao extends CrudDao<User> {

    /**
     * 根据 用户名 获取用户信息
     * @param loginName 用名登入名称
     * @return
     */
    User getByLoginName(@Param("loginName") String loginName);

    /**
     * 根据 邮箱获取用户信息
     * @param email 邮箱
     * @return
     */
    User getUserByEmail(@Param("email") String email);

}
