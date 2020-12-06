package com.gyh.config.mybatis.dialect;

/**
 * @author guoyh
 * @version 2013-3-23
 */
public interface Dialect {

    /**
     * 检测当前数据库是否支持分页
     * @return
     */
    boolean supportPage();

    /**
     * 分页sql接口
     * @param sql SQL语句
     * @param offset
     * @param limit
     * @return
     */
    String getPagingSql(String sql, int offset, int limit);

}
