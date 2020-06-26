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

    User getByLoginName(@Param("loginName") String loginName);

    User getUserByEmail(@Param("email") String email);
}
