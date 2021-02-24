package com.xuuuuu.redisdemo.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description redis 配置
 * @ClassName RedisConfig.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2021/1/25 17:10
 **/
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private long maxWaitMillis;

	@Value("${spring.redis.database:0}")
	private int database;

	// 配置 Jedis。Jedis就是Redis官方推荐的Java连接开发工具。
	// 在Java中，Redis对应于Jedis就相当于关系数据库对应于JDBC。
	@Bean
	public JedisPool redisPoolFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		if (StringUtils.isNotBlank(password)) {   // 密码判空
			return new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
		} else {
			return new JedisPool(jedisPoolConfig, host, port, timeout, null, database);
		}
	}

	// 类似于数据库连接池一样，Redis客户端也建立一个连接工厂
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		redisStandaloneConfiguration.setDatabase(database);

		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		jedisClientConfiguration.connectTimeout(Duration.ofMillis(timeout));
		jedisClientConfiguration.usePooling();
		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
	}

	// 自定义序列化方式。默认用JdkSerializationRedisSerializer进行序列化
	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}

	/**默认使用的是StringRedisSerializer
	 * StringRedisTemplate，即RedisTemplate<String, String>
	 * key和value都是String。当需要存储实体类时，需要先转为String，
	 * 再存入Redis。一般转为Json格式的字符串，
	 * 所以使用StringRedisTemplate，需要手动将实体类转为Json格式。
	 **/
	@Bean
	@ConditionalOnMissingBean(StringRedisTemplate.class)
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
		return stringRedisTemplate;
	}

	/**缓存管理器
	 * Spring缓存抽象模块通过CacheManager来创建、管理实际缓存组件，
	 * 当SpringBoot应用程序引入spring-boot-starter-data-redi依赖后，
	 * 容器中将注册的是CacheManager实例RedisCacheManager对象，
	 * RedisCacheManager来负责创建RedisCache作为缓存管理组件，
	 * 由RedisCache操作redis服务器实现缓存数据操作。
	 * 实际测试发现默认注入的RedisCacheManager操作缓存用的是RedisTemplate<Object, Object>，
	 * 因此我们需要自定义cacheManager，替换掉默认的序列化器。
	 **/
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory);
		return builder.build();
	}

	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(method.getName());
			Arrays.stream(params).map(Object::toString).forEach(sb::append);
			return sb.toString();
		};
	}
}
