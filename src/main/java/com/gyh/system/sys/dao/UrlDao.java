package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.entity.Url;

/**
 * @author gyh
 * @Date 2020/6/26 19:27
 */
public interface UrlDao extends CrudDao<Url> {

    int putAuth(Url url);
}
