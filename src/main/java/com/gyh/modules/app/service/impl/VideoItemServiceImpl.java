package com.gyh.modules.app.service.impl;

import com.gyh.common.persistence.service.BaseService;
import com.gyh.modules.app.dao.VideoDao;
import com.gyh.modules.app.dao.VideoItemDao;
import com.gyh.modules.app.entity.Video;
import com.gyh.modules.app.entity.VideoItem;
import com.gyh.modules.app.service.VideoItemService;
import org.springframework.stereotype.Service;

/**
 * @author gyh
 * @Date 2021/4/15 16:03
 */
@Service("videoItemService")
public class VideoItemServiceImpl extends BaseService<VideoItemDao, VideoItem> implements VideoItemService {

}
