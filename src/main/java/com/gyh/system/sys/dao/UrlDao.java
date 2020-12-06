package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.dto.UrlDto;
import com.gyh.system.sys.entity.Url;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/26 19:27
 */
public interface UrlDao extends CrudDao<Url> {

    /**
     * 修改 url
     * @param url
     * @return
     */
    int putAuth(Url url);

    /**
     * 获取 url的code编码
     * @return
     */
    String getMaxCode();

    /**
     * 获取 url
     * @return
     */
    List<UrlDto> getAuthUri();
}
