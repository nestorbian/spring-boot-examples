package com.nestor.resttemplateconfigdemo.config;

import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.nestor.resttemplateconfigdemo.support.CustomConnectionKeepAliveStrategy;
import com.nestor.resttemplateconfigdemo.support.CustomResponseErrorHandler;
import com.nestor.resttemplateconfigdemo.support.RestTemplateLogInterceptor;
import com.nestor.resttemplateconfigdemo.support.RestTemplateProperties;

/**
 * 自定义的RestTemplate配置类 restTemplate
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/8/27
 */
@Configuration
public class RestTemplateConfig {

	@Autowired
	private RestTemplateProperties restTemplateProperties;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder,
			ClientHttpRequestFactory clientHttpRequestFactory, RestTemplateLogInterceptor logInterceptor) {
		// 添加自定义ResponseErrorHandler，防止4xx,5xx抛出异常
		return restTemplateBuilder.requestFactory(() -> clientHttpRequestFactory).interceptors(
				logInterceptor).errorHandler(new CustomResponseErrorHandler()).build();
	}

	/**
	 * 配置client请求工厂
	 *
	 * @param httpClient
	 * @return org.springframework.http.client.HttpComponentsClientHttpRequestFactory
	 * @date : 2020/12/19 14:10
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Bean
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClient);
		clientHttpRequestFactory.setBufferRequestBody(restTemplateProperties.isBufferRequestBody());
		return clientHttpRequestFactory;
	}

	/**
	 * 配置httpClient TTL 30s
	 *
	 * @param connectionManager
	 * @return org.apache.http.client.HttpClient
	 * @date : 2020/8/28 21:12
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Bean
	public HttpClient httpClient(PoolingHttpClientConnectionManager connectionManager, RequestConfig requestConfig) {
		HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(
				connectionManager).evictIdleConnections(restTemplateProperties.getMaxIdleTime(),
						TimeUnit.SECONDS).setDefaultRequestConfig(requestConfig);

		if (restTemplateProperties.getRetryCount() > 0) {
			httpClientBuilder.setRetryHandler(getHttpRequestRetryHandler());
		} else {
			httpClientBuilder.disableAutomaticRetries();
		}

		return httpClientBuilder.setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy()).build();
	}

	/**
	 * 配置连接池
	 *
	 * @param
	 * @return org.apache.http.impl.conn.PoolingHttpClientConnectionManager
	 * @date : 2020/12/19 13:53
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Bean
	public PoolingHttpClientConnectionManager connectionManager() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
				restTemplateProperties.getTimeToLive(), TimeUnit.SECONDS);
		// 最大线程数
		connectionManager.setMaxTotal(restTemplateProperties.getMaxTotalThreads());
		// 每个route下的最大线程数
		connectionManager.setDefaultMaxPerRoute(restTemplateProperties.getMaxPerRouteThreads());
		return connectionManager;
	}

	/**
	 * 自定义的配置
	 *
	 * @param
	 * @return org.apache.http.client.config.RequestConfig
	 * @date : 2020/12/19 13:54
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Bean
	public RequestConfig requestConfig() {
		// socketTimeout=readTimeout
		return RequestConfig.custom().setSocketTimeout(restTemplateProperties.getSocketTimeout()).setConnectTimeout(
				restTemplateProperties.getConnectTimeout()).setConnectionRequestTimeout(
						restTemplateProperties.getConnectionRequestTimeout()).build();
	}

	/**
	 * 请求重试
	 *
	 * @param
	 * @return org.apache.http.client.HttpRequestRetryHandler
	 * @date : 2020/12/19 13:54
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Bean
	public HttpRequestRetryHandler getHttpRequestRetryHandler() {
		return (exception, executionCount, context) -> {
			if (executionCount >= restTemplateProperties.getRetryCount()) { // 在配置的超时时间内，如果重试了N次，就放弃
				return false;
			}
			if (exception instanceof NoHttpResponseException) { // 如果服务器丢掉了连接，那么就重试
				return true;
			}
			if (exception instanceof SSLHandshakeException) { // 不要重试SSL握手异常
				return false;
			}
			if (exception instanceof InterruptedIOException) { // 超时
				return false;
			}
			if (exception instanceof UnknownHostException) { // 目标服务器不可达
				return false;
			}
			if (exception instanceof ConnectTimeoutException) { // 连接被拒绝
				return false;
			}
			if (exception instanceof SSLException) { // SSL握手异常
				return false;
			}

			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			// 如果请求是幂等，就再次重试
			return request instanceof HttpEntityEnclosingRequest;
		};
	}

}
