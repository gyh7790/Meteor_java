package com.gyh.system.sys.utils;

import com.gyh.common.tools.ListUtils;
import com.gyh.system.sys.entity.Role;
import com.gyh.system.sys.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2020/6/13 23:19
 */
public class UserUtils {

    public static User get() {
        Authentication sc = SecurityContextHolder.getContext().getAuthentication();
        return (User)sc.getPrincipal();
    }

    public static List<String> getRoleIds(){
        List<Role> roles = get().getRoles();
        if (ListUtils.isNotEmpty(roles)) {
            return roles.stream().map(Role::getId).collect(Collectors.toList());
        }
        return null;
    }

}
