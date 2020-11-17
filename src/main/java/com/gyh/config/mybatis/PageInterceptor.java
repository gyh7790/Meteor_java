package com.gyh.config.mybatis;

import com.gyh.common.persistence.model.Page;
import com.gyh.config.mybatis.dialect.impl.MysqlDialect;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author guoyh
 * @Date 2019/5/31 23:52
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor extends BaseInterceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 获取被拦截的方法的参数列表
        Object[] queryArgs = invocation.getArgs();

        MappedStatement ms = (MappedStatement) queryArgs[MAPPEDSTATEMENT_INDEX];

        Object parameter = queryArgs[PARAMETEROBJECT_INDEX];

        BoundSql boundSql = ms.getBoundSql(parameter);

        Object parameterObject = boundSql.getParameterObject();


        Page<Object> page = null;

        //判断 是否支持分页
        if (parameterObject != null) {
            page = convertParameter(parameterObject,page);
        }

        if (page != null && page.getPageSize() != -1) {
            String sql = boundSql.getSql().trim();
            page.setTotal(SQLHelper.getCount(sql, null, ms, parameterObject, boundSql,log));

            String pageSql = SQLHelper.generatePageSql(sql, page, new MysqlDialect());

            BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());

            invocation.getArgs()[0] = createMappedStatement( ms, new BoundSqlSqlSource(newBoundSql));
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {
        super.initProperties(properties);
    }

    private MappedStatement createMappedStatement(MappedStatement ms, SqlSource newSqlSource){

        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }


    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}