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

    /**
     * 批量插入 用户-角色
     * @param list
     * @return
     */
    int insertUserAndRole(List<UserRole> list);

    /**
     * 删除 用户-角色
     * @param roleId 角色ID
     * @return
     */
    int deleteByRoleId(@Param("roleId") String roleId);

    /**
     * 删除 用户-角色
     * @param userId 用户ID
     * @return
     */
    int deleteByUserId(@Param("userId") String userId);
}
