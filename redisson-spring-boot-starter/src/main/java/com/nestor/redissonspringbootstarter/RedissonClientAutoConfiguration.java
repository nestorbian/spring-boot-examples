package com.nestor.redissonspringbootstarter;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * redis 客户端自动配置
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/4/9
 */
@Configuration
@ConditionalOnClass(Redisson.class)
@EnableConfigurationProperties(RedissonClientProperties.class)
public class RedissonClientAutoConfiguration {

	@Bean
	public RedissonClient redissonClient(RedissonClientProperties redissonClientProperties) {
		Config config = new Config();

		String prefix = "redis://";
		if (redissonClientProperties.isSsl()) {
			prefix = "rediss://";
		}

		SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(
				prefix + redissonClientProperties.getHost() + ":" + redissonClientProperties.getPort()).setDatabase(
						redissonClientProperties.getDatabase()).setConnectTimeout(
								redissonClientProperties.getTimeout());

		if (StringUtils.hasLength(redissonClientProperties.getPassword())) {
			singleServerConfig.setPassword(redissonClientProperties.getPassword());
		}

		return Redisson.create(config);
	}

    public static void main(String[] args) {
        System.err.println(Runtime.getRuntime().availableProcessors());
    }

}
