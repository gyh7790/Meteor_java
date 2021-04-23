package com.gyh.modules.app.service;

import com.gyh.modules.app.entity.Banner;
import com.gyh.modules.app.entity.Video;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:01
 */
public interface VideoService {

    /**
     * 获取 视频
     * @param video
     * @return
     */
    List<Video> findList(Video video);

    /**
     * 添加视频信息
     * @param video
     * @return
     */
    int save(Video video);

    /**
     * 删除视频
     * @param id
     * @return
     */
    int deleteById(String id);
}
