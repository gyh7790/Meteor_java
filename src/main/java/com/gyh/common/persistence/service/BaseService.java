package com.gyh.common.persistence.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author
 * @Date 2019/5/7 21:29
 */
//@Transactional(readOnly = true)
public abstract class BaseService {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());


}
