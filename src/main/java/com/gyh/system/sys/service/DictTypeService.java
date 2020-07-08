package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.CrudService;
import com.gyh.system.sys.dao.DictDataDao;
import com.gyh.system.sys.dao.DictTypeDao;
import com.gyh.system.sys.entity.DictData;
import com.gyh.system.sys.entity.DictType;
import org.springframework.stereotype.Service;

/**
 * @author gyh
 * @Date 2020/7/3 15:23
 */
@Service
public class DictTypeService extends CrudService<DictTypeDao, DictType> {

}
