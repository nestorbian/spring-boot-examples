package com.nestor.resttemplateconfigdemo.support;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常处理器:响应码=4xx或者5xx不抛出异常，下游服务会有全局异常处理器，5xx状态仅标志下游服务发生错误
 *
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/12/19
 */
@Slf4j
public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

	/**
	 * Indicate whether the given response has any errors.
	 * <p>
	 * Implementations will typically inspect the {@link ClientHttpResponse#getStatusCode() HttpStatus} of the response.
	 *
	 * @param response
	 *            the response to inspect
	 * @return {@code true} if the response indicates an error; {@code false} otherwise
	 * @throws IOException
	 *             in case of I/O errors
	 */
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
        int rawStatusCode = response.getRawStatusCode();
        HttpStatus statusCode = HttpStatus.resolve(rawStatusCode);

        if (Objects.isNull(statusCode)) {
            HttpStatus.Series series = HttpStatus.Series.resolve(rawStatusCode);
            return Objects.isNull(series);
        }

		return false;
	}

	/**
	 * Handle the error in the given response.
	 * <p>
	 * This method is only called when {@link #hasError(ClientHttpResponse)} has returned {@code true}.
	 *
	 * @param response
	 *            the response with the error
	 * @throws IOException
	 *             in case of I/O errors
	 */
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		byte[] body = getResponseBody(response);
		String message = getErrorMessage(response.getRawStatusCode(),
				response.getStatusText(), body, getCharset(response));
		throw new UnknownHttpStatusCodeException(message,
				response.getRawStatusCode(), response.getStatusText(),
				response.getHeaders(), body, getCharset(response));
	}

	/**
	 * Alternative to {@link #handleError(ClientHttpResponse)} with extra information providing access to the request
	 * URL and HTTP method.
	 *
	 * @param url
	 *            the request URL
	 * @param method
	 *            the HTTP method
	 * @param response
	 *            the response with the error
	 * @throws IOException
	 *             in case of I/O errors
	 * @since 5.0
	 */
	@Override
	public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
		log.warn("restTemplate异常处理: 下游服务器发生错误, url:[{}], method:[{}], http状态码:[{}]", url.getPath(), method.name(),
				response.getRawStatusCode());
        handleError(response);
	}

	private String getErrorMessage(
			int rawStatusCode, String statusText, @Nullable byte[] responseBody, @Nullable Charset charset) {

		String preface = rawStatusCode + " " + statusText + ": ";
		if (ObjectUtils.isEmpty(responseBody)) {
			return preface + "[no body]";
		}

		charset = charset == null ? StandardCharsets.UTF_8 : charset;
		int maxChars = 200;

		if (responseBody.length < maxChars * 2) {
			return preface + "[" + new String(responseBody, charset) + "]";
		}

		try {
			Reader reader = new InputStreamReader(new ByteArrayInputStream(responseBody), charset);
			CharBuffer buffer = CharBuffer.allocate(maxChars);
			reader.read(buffer);
			reader.close();
			buffer.flip();
			return preface + "[" + buffer.toString() + "... (" + responseBody.length + " bytes)]";
		}
		catch (IOException ex) {
			// should never happen
			throw new IllegalStateException(ex);
		}
	}
}
