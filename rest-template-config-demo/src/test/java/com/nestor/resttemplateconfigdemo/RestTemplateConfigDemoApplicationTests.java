package com.nestor.resttemplateconfigdemo;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
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
			// URI uri = new URIBuilder("http://127.0.0.1:8080/mybatis-demo/grade-params/{name}/{level}").addParameter(
			// "name", "1").addParameter("level", "1").build();
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

	/**
	 * 测试Http GET请求是否可以添加JSON请求体:不支持
	 * 但是postman是支持的
	 *
	 * @param
	 * @return void
	 * @date : 2021/1/20 22:38
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Test
	public void getMethodTest() {
		HashMap<String, Object> hashMap = new HashMap<>(8);
		hashMap.put("name", "1");
		URI uri = UriComponentsBuilder.fromUriString(
				"http://127.0.0.1:8080/mybatis-demo/grade-params/like?name={name}").build().expand(hashMap).toUri();
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(hashMap, multiValueMap);
		ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		System.err.println(exchange.getBody());
	}

	/**
	 * post表单请求
	 * 请求体需要使用MultiValueMap
	 *
	 * @param
	 * @return void
	 * @date : 2021/1/20 22:57
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Test
	public void postMethodWithFormTest() {
		MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>(8);
		valueMap.add("name", "1");
		URI uri = UriComponentsBuilder.fromUriString(
				"http://127.0.0.1:8080/mybatis-demo/grade-param").build().toUri();
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		// multiValueMap.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		// HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(valueMap, multiValueMap);
		// ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
		ResponseEntity<String> exchange = restTemplate.postForEntity(uri, valueMap, String.class);
		System.err.println(exchange.getBody());
	}

	/**
	 * 测试	private long timeToLive = 30L;
	 *
	 * @param
	 * @return void
	 * @date : 2023/5/14 1:56
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Test
	public void getMethodTest1() {
		String result = restTemplate.getForObject("https://www.baidu.com/abc", String.class);
		System.err.println(result);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String result1 = restTemplate.getForObject("https://www.baidu.com/abc", String.class);
		System.err.println(result1);

		// try {
		// 	Thread.sleep(20000);
		// } catch (InterruptedException e) {
		// 	e.printStackTrace();
		// }
		//
		// String result2 = restTemplate.getForObject("https://www.baidu.com/abc", String.class);
		// System.err.println(result2);
	}

}
