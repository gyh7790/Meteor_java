package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/10/22 23:20
 */
public interface UserRoleDao extends CrudDao<UserRole> {

    int insertUserAndRole(List<UserRole> list);

    int deleteByRoleId(@Param("roleId") String roleId);

    int deleteByUserId(@Param("userId") String userId);
}
