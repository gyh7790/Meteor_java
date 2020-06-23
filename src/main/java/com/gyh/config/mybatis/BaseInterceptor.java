package com.gyh.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.gyh.common.persistence.model.Page;
import com.gyh.common.tools.Reflections;
import com.gyh.config.mybatis.dialect.Dialect;
import com.gyh.config.mybatis.dialect.impl.MysqlDialect;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author guoyh
 * @Date 2019/5/31 23:25
 */
public abstract class BaseInterceptor extends PaginationInterceptor implements Interceptor, Serializable {

    protected Log log = LogFactory.getLog(this.getClass());

    protected static int MAPPEDSTATEMENT_INDEX = 0;

    protected static int PARAMETEROBJECT_INDEX = 1;

    protected static int ROWBOUNDS_INDEX = 2;

    protected static final String PAGE = "page";

    protected Dialect dialect;

    /**
     * 检测 使用的数据库
     */
    protected void initProperties(Properties properties) {
        dialect = new MysqlDialect();
    }




    protected static Page<Object> convertParameter(Object parameterObject, Page<Object> page) {
        try{
            if (parameterObject instanceof Page) {
                return (Page<Object>) parameterObject;
            } else {
                return (Page<Object>) Reflections.getFieldValue(parameterObject, PAGE);
            }
        }catch (Exception e) {
            return null;
        }
    }

}
