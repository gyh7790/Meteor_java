package com.gyh.common.persistence.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/6/11 23:48
 */
@Mapper
public interface CrudDao <T> extends BaseDao {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    T get(@Param("id") String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    T get(T entity);

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * 查询所有数据列表
     * @param entity
     * @return
     */
    List<T> findAllList(T entity);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 批量 新增数据
     * @param list
     * @return
     */
    int insertList(@Param("list") List<T> list);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @param entity
     * @return
     */
    int remove(T entity);

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @param id 需要产出的ID
     * @return
     */
    int deleteById(String id);

}