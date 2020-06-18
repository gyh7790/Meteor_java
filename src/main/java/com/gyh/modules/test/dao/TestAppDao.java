package com.gyh.modules.test.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.modules.test.entity.TestApp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/11 23:01
 */
public interface TestAppDao extends CrudDao {

    List<TestApp> findList();
}
