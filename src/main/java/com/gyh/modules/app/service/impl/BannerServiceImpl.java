package com.gyh.modules.app.service.impl;

import com.gyh.common.persistence.service.BaseService;
import com.gyh.modules.app.dao.BannerDao;
import com.gyh.modules.app.entity.Banner;
import com.gyh.modules.app.service.BannerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:00
 */
@Service("bannerService")
public class BannerServiceImpl extends BaseService<BannerDao, Banner> implements BannerService {

    @Override
    public List<Banner> findList(Banner banner) {
        return dao.findList(banner);
    }
}
