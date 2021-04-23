package com.gyh.modules.app.service;

import com.gyh.modules.app.entity.Tag;
import com.gyh.modules.app.entity.Video;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:01
 */
public interface TagService {

    List<Tag> findList(Tag tag);

    int save(Tag tag);

    int deleteById(String id);
}
