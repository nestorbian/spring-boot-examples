package com.nestor.resttemplateconfigdemo;

import java.net.URI;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * restTemplate功能测试
 *
 * @date : 2020/12/4 11:21
 * @author : Nestor.Bian
 * @since : 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class RestTemplateConfigDemoApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpClient httpClient;

	/**
	 * 1. 未加ResponseErrorHandler默认遇到响应码4xx和5xx会直接抛出异常HttpServerErrorException 2.
	 * 加上自定义ResponseErrorHandler，1-5开头异常码不会抛出异常，只有
	 *
	 * @param
	 * @return void
	 * @date : 2020/12/19 14:08
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Test
	public void getMethodUsingRestTemplateTest() {
		try {
			URI uri = UriComponentsBuilder.fromUriString(
					"http://127.0.0.1:8080/mybatis-demo/grade-params/{name}/{level}").build("1", "1");
			ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY,
					String.class);
			if (!Objects.isNull(exchange.getStatusCode()) && exchange.getStatusCode() == HttpStatus.OK) {
				// 2xx 走这里
				System.err.println("[SUCCESS] body:" + exchange.getBody());
			} else {
				// 5xx 4xx 不会走这里,直接抛出异常HttpServerErrorException
				System.err.println("[ERROR] status:" + exchange.getStatusCodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getMethodUsingHttpClientTest() {
		try {
			URI uri = UriComponentsBuilder.fromUriString(
					"http://127.0.0.1:8080/mybatis-demo/grade-params/{name}/{level}").build("1", "1");
			HttpGet httpGet = new HttpGet(uri);
			HttpResponse execute = httpClient.execute(httpGet);
			if (!Objects.isNull(execute.getStatusLine())
					&& execute.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {
				// 2xx 走这里
				System.err.println("[SUCCESS] body:" + EntityUtils.toString(execute.getEntity()));
			} else {
				// 5xx 4xx 会走这里
				System.err.println("[ERROR] status:" + execute.getStatusLine().getStatusCode() + ", body:"
						+ EntityUtils.toString(execute.getEntity()));
			}
		} catch (Exception e) {
			// 超时走这里
			e.printStackTrace();
		}
	}

}
