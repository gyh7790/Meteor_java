package com.gyh.common.persistence.service;

import com.gyh.common.persistence.base.BaseEntity;
import com.gyh.common.persistence.base.CrudDao;
import com.gyh.common.persistence.model.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @Date 2019/5/7 21:27
 */
//@Transactional(readOnly = true)
public abstract class BaseService<D extends CrudDao<T>,T extends BaseEntity<T>> extends AbstractService {

    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    public Page<T> findPage(Page<T> page, T entity){
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;
    }

    /**
     * 查询所有数据列表
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public List<T> findAllList(T entity) {
        return dao.findAllList(entity);
    }

    /**
     * 插入数据
     * @return
     */
    @Transactional(readOnly = false)
    public int insert(T entity) {
        entity.preInsert();
        return dao.insert(entity);
    }

    /**
     * 批量 新增数据
     * @param list
     * @return
     */
    @Transactional(readOnly = false)
    public int insertList(List<T> list) {
        for (T t: list) {
            t.preInsert();
        }
        return dao.insertList(list);
    }

    /**
     * 更新数据
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int update(T entity) {
        entity.preUpdate();
        return dao.update(entity);
    }

    /**
     * 保存数据,
     * 如果有ID执行修改
     * 如果没有ID执行新增
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int save(T entity){
        if (StringUtils.isNotEmpty(entity.getId() )) {
            entity.preUpdate();
            return update(entity);
        } else {
            entity.preInsert();
            return insert(entity);
        }
    }

    /**
     * 批量 保存数据
     * @return
     */
    @Transactional(readOnly = false)
    public int saveList(List<T> entityList) {
        int row = 0;
        for (T t : entityList) {
            row = row + save(t);
        }
        return row;
    }


    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int remove(T entity) {
        return dao.remove(entity);
    }

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     * @return
     */
    @Transactional(readOnly = false)
    public int deleteById(String id) {
        return dao.deleteById(id);
    }

}
