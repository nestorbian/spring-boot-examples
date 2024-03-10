package com.nestor.resttemplateconfigdemo.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * RestTemplate配置属性
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/8/30
 */
@Component
@ConfigurationProperties("rest")
@Data
public class RestTemplateProperties {
	// 默认true, 请求头过大时建议使用false
	private boolean bufferRequestBody = true;
	// 默认120s 连接的最大空闲时间
	private long maxIdleTime = 120L;
	// TTL 默认600s 连接最大存活时间建议配置长一点
	private long timeToLive = 600L;
	// 线程池最大连接数
	private int maxTotalThreads = 100;
	// 每个route下的最大连接数
	private int maxPerRouteThreads = 10;
	private int socketTimeout = 5000;
	private int connectTimeout = 2000;
	private int connectionRequestTimeout = 500;
	// 重试次数
	private int retryCount = 0;
}
