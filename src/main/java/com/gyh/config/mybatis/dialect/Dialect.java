package com.gyh.config.mybatis.dialect;

public interface Dialect {

    /**
     * 检测当前数据库是否支持分页
     * @return
     */
    public boolean supportPage();


    public String getPagingSql(String sql, int offset, int limit);

}
