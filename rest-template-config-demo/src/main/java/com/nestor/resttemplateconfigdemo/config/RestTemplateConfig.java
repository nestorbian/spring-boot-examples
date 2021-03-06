package com.nestor.resttemplateconfigdemo.config;

import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import com.nestor.resttemplateconfigdemo.support.RestTemplateProperties;
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
import com.nestor.resttemplateconfigdemo.support.RestTemplateLogInterceptor;

/**
 * 自定义的RestTemplate配置类
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
		return restTemplateBuilder.requestFactory(() -> clientHttpRequestFactory).interceptors(logInterceptor).build();
	}

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

	@Bean
	public RequestConfig requestConfig() {
		// socketTimeout=readTimeout
		return RequestConfig.custom().setSocketTimeout(restTemplateProperties.getSocketTimeout()).setConnectTimeout(
				restTemplateProperties.getConnectTimeout()).setConnectionRequestTimeout(
						restTemplateProperties.getConnectionRequestTimeout()).build();
	}

	@Bean
	public HttpRequestRetryHandler getHttpRequestRetryHandler() {
		return (exception, executionCount, context) -> {
			if (executionCount >= restTemplateProperties.getRetryCount()) {
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				return true;
			}
			if (exception instanceof SSLHandshakeException) {
				return false;
			}
			if (exception instanceof InterruptedIOException) {
				return false;
			}
			if (exception instanceof UnknownHostException) {
				return false;
			}
			if (exception instanceof ConnectTimeoutException) {
				return false;
			}
			if (exception instanceof SSLException) {
				return false;
			}

			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			return request instanceof HttpEntityEnclosingRequest;
		};
	}

}
