package com.nestor.resttemplateconfigdemo.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * RestTemplate的日志打印拦截器
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/8/27
 */
@Slf4j
@Component
public class RestTemplateLogInterceptor implements ClientHttpRequestInterceptor {
	/**
	 * Intercept the given request, and return a response. The given {@link ClientHttpRequestExecution} allows the
	 * interceptor to pass on the request and response to the next entity in the chain.
	 * <p>
	 * A typical implementation of this method would follow the following pattern:
	 * <ol>
	 * <li>Examine the {@linkplain HttpRequest request} and body</li>
	 * <li>Optionally {@linkplain HttpRequestWrapper wrap} the request to filter HTTP attributes.</li>
	 * <li>Optionally modify the body of the request.</li>
	 * <li><strong>Either</strong>
	 * <ul>
	 * <li>execute the request using {@link ClientHttpRequestExecution#execute(HttpRequest, byte[])},</li>
	 * <strong>or</strong>
	 * <li>do not execute the request to block the execution altogether.</li>
	 * </ul>
	 * <li>Optionally wrap the response to filter HTTP attributes.</li>
	 * </ol>
	 *
	 * @param request
	 *            the request, containing method, URI, and headers
	 * @param body
	 *            the body of the request
	 * @param execution
	 *            the request execution
	 * @return the response
	 * @throws IOException
	 *             in case of I/O errors
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		log.info("restTemplate发起http请求, 请求地址:[{}], 请求方法:[{}], 请求头:[{}], 请求体:[{}]", request.getURI(),
				request.getMethod(), request.getHeaders(), new String(body));

		ClientHttpResponse httpResponse = execution.execute(request, body);

        log.info("restTemplate接收http响应, http状态码:[{}], 响应头:[{}], 响应体:[{}]", httpResponse.getRawStatusCode(),
                httpResponse.getHeaders(), httpResponse.getBody());

		return httpResponse;
	}
}
