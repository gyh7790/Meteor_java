package com.gyh.modules.test.service;

import com.gyh.modules.test.dao.TestAppDao;
import com.gyh.modules.test.entity.TestApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/11 23:09
 */
@Service
public class TestAppService {

    @Autowired
    private TestAppDao testAppDao;

//    @Cacheable(cacheNames = {"TestAppService"})
    public List<TestApp> findList(){
        return testAppDao.findList();
    }
}
