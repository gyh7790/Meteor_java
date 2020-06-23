package com.gyh.config.mybatis.dialect.impl;


import com.gyh.config.mybatis.dialect.Dialect;

/**
 * @author guoyh
 * @Date 2019/5/31 23:45
 */
public class MysqlDialect implements Dialect {


    @Override
    public boolean supportPage() {
        return true;
    }

    @Override
    public String getPagingSql(String sql, int offset, int limit) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        stringBuilder.append(" limit ");
        if (offset > 0) {
            stringBuilder.append(offset).append(",").append(limit);
        } else {
            stringBuilder.append(limit);
        }
        return stringBuilder.toString();
    }
}
