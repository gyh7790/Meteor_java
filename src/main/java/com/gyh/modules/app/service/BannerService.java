package com.gyh.modules.app.service;

import com.gyh.modules.app.entity.Banner;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 15:59
 */
public interface BannerService {

    /**
     * 获取 轮播信息
     * @param banner
     * @return
     */
    List<Banner> findList(Banner banner);

    /**
     * 保存 轮播图
     * @param banner
     * @return
     */
    int save(Banner banner);

    /**
     * 删除 轮播
     * @param id
     * @return
     */
    int deleteById(String id);
}
