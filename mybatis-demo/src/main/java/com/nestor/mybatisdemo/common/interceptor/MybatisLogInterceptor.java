package com.nestor.mybatisdemo.common.interceptor;

import java.util.Objects;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.nestor.mybatisdemo.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * SQL打印拦截器
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/3/18
 */
@Slf4j
@Intercepts(value = {
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
public class MybatisLogInterceptor implements Interceptor {

	// 是否打印SQL
	private boolean logSql = true;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		Object parameter = args[1];
		BoundSql boundSql = ms.getSqlSource().getBoundSql(parameter);

		if (logSql) {
			log.info("mybatis sql=[{}], mapper id=[{}], mapper file=[{}], parameters=[{}], parameter class=[{}]",
					boundSql.getSql(), ms.getId(), ms.getResource(), JsonUtil.toJsonString(parameter),
					Objects.isNull(parameter) ? null : parameter.getClass().getCanonicalName());
		}

		// 其他事情如：埋点...
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// nothing
	}

	public boolean isLogSql() {
		return logSql;
	}

	public void setLogSql(boolean logSql) {
		this.logSql = logSql;
	}
}
