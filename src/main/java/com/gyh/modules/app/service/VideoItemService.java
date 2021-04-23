package com.gyh.modules.app.service;

import com.gyh.modules.app.entity.VideoItem;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:02
 */
public interface VideoItemService {

    /**
     * 查询 视频详情
     * @param videoItem
     * @return
     */
    List<VideoItem> findList(VideoItem videoItem);

    /**
     * 添加 视频详情
     * @param videoItem
     * @return
     */
    int save(VideoItem videoItem);

    /**
     * 删除 视频详情
     * @param id
     * @return
     */
    int deleteById(String id);
}
