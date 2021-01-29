package com.nestor.mybatisdemo.service.impl;

import com.nestor.mybatisdemo.po.GradeParam;
import com.nestor.mybatisdemo.service.GradeParamService;
import com.nestor.mybatisdemo.util.EscapeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class GradeParamServiceImplTest {

	@Autowired
	private GradeParamService gradeParamService;

	/**
	 * 等值查询\预处理是否会转义：是的
     * like 预处理\与其他字符连用不会转义，针对用户输入的%、_、\需要我们自己转义
	 *
	 * @param
	 * @return void
	 * @date : 2021/1/15 23:47
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Test
	void selectByNameLike() {
		List<GradeParam> gradeParams = gradeParamService.selectByNameLike("aaa\\");
		log.info("结果: [{}]", gradeParams);
	}


	/**
	 * 思考：plugin源码中Map.get(method.getDeclaringClass())来取Set<Method>能否取到
     * plugin中被代理类仅有一个上层接口类
     * proxy中的method.getDeclaringClass()获取的是接口类
	 *
	 * @param
	 * @return void
	 * @date : 2021/1/15 23:47
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Test
    void methodDeclaringClass() throws Exception {
        System.err.println(Arrays.toString(BaseExecutor.class.getMethods()));

        BExecutor bExecutor = new BExecutor();
        Object proxyInstance = Proxy.newProxyInstance(bExecutor.getClass().getClassLoader(), new Class[]{Executor.class}, (proxy, method, args) -> {
            System.err.println(method.getDeclaringClass());
            return method.invoke(bExecutor, args);
        });
        Executor executor = (Executor) proxyInstance;
        executor.isClosed();
    }

	public class BExecutor implements Executor {

		@Override
		public int update(MappedStatement ms, Object parameter) throws SQLException {
			return 0;
		}

		@Override
		public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
				CacheKey cacheKey, BoundSql boundSql) throws SQLException {
			return null;
		}

		@Override
		public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
				throws SQLException {
			return null;
		}

		@Override
		public <E> Cursor<E> queryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds)
				throws SQLException {
			return null;
		}

		@Override
		public List<BatchResult> flushStatements() throws SQLException {
			return null;
		}

		@Override
		public void commit(boolean required) throws SQLException {

		}

		@Override
		public void rollback(boolean required) throws SQLException {

		}

		@Override
		public CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds,
				BoundSql boundSql) {
			return null;
		}

		@Override
		public boolean isCached(MappedStatement ms, CacheKey key) {
			return false;
		}

		@Override
		public void clearLocalCache() {

		}

		@Override
		public void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key,
				Class<?> targetType) {

		}

		@Override
		public Transaction getTransaction() {
			return null;
		}

		@Override
		public void close(boolean forceRollback) {

		}

		@Override
		public boolean isClosed() {
			return false;
		}

		@Override
		public void setExecutorWrapper(Executor executor) {

		}
	}
}