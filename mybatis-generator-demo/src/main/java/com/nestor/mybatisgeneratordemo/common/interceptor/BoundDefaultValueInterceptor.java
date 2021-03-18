package com.nestor.mybatisgeneratordemo.common.interceptor;

import com.nestor.mybatisgeneratordemo.po.BaseModel;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * 针对于SQL的insert、update方法 绑定实体类的默认值的拦截器
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/3/18
 */
@Intercepts(value = @Signature(type = Executor.class, method = "update", args = { MappedStatement.class,
		Object.class }))
public class BoundDefaultValueInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// ((Executor) invocation.getTarget()).getTransaction().getConnection().getMetaData().getUserName()
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		Object parameter = args[1];

		SqlCommandType sqlCommandType = ms.getSqlCommandType();

		if (sqlCommandType != SqlCommandType.INSERT && sqlCommandType != SqlCommandType.UPDATE) {
			return invocation.proceed();
		}

		if (parameter instanceof BaseModel) {

			boundDefaultVale(sqlCommandType == SqlCommandType.INSERT, (BaseModel) parameter);

		} else if (parameter instanceof DefaultSqlSession.StrictMap) {
			// see DefaultSqlSession的wrapCollection方法
			DefaultSqlSession.StrictMap strictMap = (DefaultSqlSession.StrictMap) parameter;
			Collection collection = (Collection) strictMap.get("collection");

			for (Object o : collection) {
				if (o instanceof BaseModel) {
					boundDefaultVale(sqlCommandType == SqlCommandType.INSERT, (BaseModel) o);
				}
			}
		} else if (parameter instanceof Map) {
			Map map = (Map) parameter;
			Object record = map.get("record");

			if (record instanceof BaseModel) {
				boundDefaultVale(sqlCommandType == SqlCommandType.INSERT, (BaseModel) record);
			}
		}

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

	/**
	 * 绑定默认值
	 *
	 * @param baseModel
	 * @return void
	 * @date : 2021/3/18 15:25
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	private void boundDefaultVale(boolean insert, BaseModel baseModel) {
		if (insert) {
			// 插入方法给默认值
			if (!baseModel.isTimeSet()) {
				baseModel.setCreateTime(LocalDateTime.now());
				baseModel.setUpdateTime(LocalDateTime.now());
			}
			// ... 一些额外默认值
			return;
		}

		// 更新方法给默认值
		if (!baseModel.isTimeSet()) {
			baseModel.setUpdateTime(LocalDateTime.now());
		}

		// ... 一些额外默认值
	}
}
