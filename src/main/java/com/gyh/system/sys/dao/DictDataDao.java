package com.gyh.system.sys.dao;

import com.gyh.common.persistence.base.CrudDao;
import com.gyh.system.sys.dto.DictDto;
import com.gyh.system.sys.entity.DictData;
import com.gyh.system.sys.entity.DictType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/7/3 15:22
 */
public interface DictDataDao extends CrudDao<DictData> {

    /**
     * 根据 类型 获取字典数据
     * @param type 字典类型
     * @return
     */
    List<DictData> getListByType(@Param("type") String type);

    /**
     * 根据 ID 修改默认值
     * @param id 分类数据 ID
     * @param defaults 是否为 默认值
     */
    void setDefaultById(@Param("id") String id,@Param("defaults") Integer defaults);

    /**
     * 根据 dictType 修改默认值
     * @param dictType 分类数据 type
     * @param defaults 是否为 默认值
     */
    void setDefaultByType(@Param("dictType") String dictType,@Param("defaults") Integer defaults);

    List<DictDto> getDictData(@Param("types") List<String> types);
}
