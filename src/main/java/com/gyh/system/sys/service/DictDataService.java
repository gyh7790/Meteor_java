package com.gyh.system.sys.service;

import com.gyh.common.persistence.service.BaseService;
import com.gyh.system.sys.dao.DictDataDao;
import com.gyh.system.sys.dao.MenuDao;
import com.gyh.system.sys.dto.DictDto;
import com.gyh.system.sys.entity.DictData;
import com.gyh.system.sys.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2020/7/3 15:22
 */
@Service
public class DictDataService extends BaseService<DictDataDao, DictData> {

    public List<DictData> getListByType(String type){
        return dao.getListByType(type);
    }

    public Map<String,List<DictDto>> getDictData(List<String> types){
        List<DictDto> list = dao.getDictData(types);
        return list.stream().collect(Collectors.groupingBy(DictDto::getDictType));
    }

    /**
     * 根据 ID 修改默认值
     * @param id 分类数据 ID
     * @param defaults 是否为 默认值
     */
    public void setDefaultById(String id, Integer defaults) {
        dao.setDefaultById(id,defaults);
    }

    /**
     * 根据 TYPE 修改默认值
     * @param dictType 分类数据 type
     * @param defaults 是否为 默认值
     */
    public void setDefaultByType(String dictType, Integer defaults) {
        dao.setDefaultByType(dictType,defaults);
    }
}
