package com.nestor.springabstractcacheredis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * 缓存异常处理机制
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/1/30
 */
@Configuration
@Slf4j
public class RedisErrorHandlerConfig extends CachingConfigurerSupport {

	@Override
	public CacheErrorHandler errorHandler() {
		return new CacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("redis get 异常：cacheName=[{}], key=[{}]", cache.getName(), key, e);
			}

			@Override
			public void handleCachePutError(RuntimeException e, Cache cache, Object key, @Nullable Object value) {
                log.error("redis put 异常：cacheName=[{}], key=[{}]", cache.getName(), key, e);
			}

			@Override
			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
				log.error("redis evict 异常：cacheName=[{}], key=[{}]", cache.getName(), key, e);
			}

			@Override
			public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("redis clear 异常：cacheName=[{}]", cache.getName(), e);
			}
		};
	}
}
