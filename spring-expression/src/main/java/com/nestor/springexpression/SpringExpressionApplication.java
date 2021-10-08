package com.nestor.springexpression;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringExpressionApplication implements ApplicationRunner {

	@Value("${boy-name:}")
	private String boyName;

	@Value("${boy-age:}")
	private String boyAge;

	@Value("${boy-score:noValue}")
	private String boyScore;

	@Value("${boy-address:}")
	private String boyAddress;

	public static void main(String[] args) {
		SpringApplication.run(SpringExpressionApplication.class, args);
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming application arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.err.println(boyAge);
		System.err.println(boyName);
		System.err.println(boyScore);
		System.err.println(boyAddress);
	}
}
