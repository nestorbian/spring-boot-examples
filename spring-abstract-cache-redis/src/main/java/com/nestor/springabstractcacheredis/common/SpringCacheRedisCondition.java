package com.nestor.springabstractcacheredis.common;

import com.nestor.springabstractcacheredis.config.RedisConfig;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;

/**
 * spring cache redis 判断类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/1/13
 */
public class SpringCacheRedisCondition implements Condition {
	/**
	 * Determine if the condition matches.
	 *
	 * @param context
	 *            the condition context
	 * @param metadata
	 *            the metadata of the {@link AnnotationMetadata class} or {@link MethodMetadata method} being checked
	 * @return {@code true} if the condition matches and the component can be registered, or {@code false} to veto the
	 *         annotated component's registration
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment environment = context.getEnvironment();
		Binder binder = Binder.get(environment);
		BindResult<CacheType> bindResult = binder.bind("spring.cache.type", CacheType.class);

		String sourceClassName = "";
		if (metadata instanceof ClassMetadata) {
			sourceClassName = ((ClassMetadata) metadata).getClassName();
		}

        //redis cache 启用 并且 是自定义的RedisConfig
		if (bindResult.isBound() && bindResult.get() == CacheType.REDIS
				&& sourceClassName.equals(RedisConfig.class.getTypeName())) {
			return true;
		}

		return false;
	}
}
