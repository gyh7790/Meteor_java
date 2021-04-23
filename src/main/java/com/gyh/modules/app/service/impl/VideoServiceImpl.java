package com.gyh.modules.app.service.impl;

import com.gyh.common.persistence.service.BaseService;
import com.gyh.modules.app.dao.BannerDao;
import com.gyh.modules.app.dao.VideoDao;
import com.gyh.modules.app.entity.Banner;
import com.gyh.modules.app.entity.Video;
import com.gyh.modules.app.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:04
 */
@Service("videoService")
public class VideoServiceImpl extends BaseService<VideoDao, Video> implements VideoService {

}
