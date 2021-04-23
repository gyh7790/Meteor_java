package com.gyh.modules.app.service.impl;

import com.gyh.common.persistence.service.BaseService;
import com.gyh.modules.app.dao.TagDao;
import com.gyh.modules.app.dao.VideoItemDao;
import com.gyh.modules.app.entity.Tag;
import com.gyh.modules.app.entity.VideoItem;
import com.gyh.modules.app.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author gyh
 * @Date 2021/4/15 16:03
 */
@Service("tagService")
public class TagServiceImpl extends BaseService<TagDao, Tag> implements TagService {

}
