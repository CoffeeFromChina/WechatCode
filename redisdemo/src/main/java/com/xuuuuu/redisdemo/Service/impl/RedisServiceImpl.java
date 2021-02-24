package com.xuuuuu.redisdemo.Service.impl;

import com.xuuuuu.redisdemo.Service.RedisService;
import com.xuuuuu.redisdemo.function.JedisExecutor;
import com.xuuuuu.redisdemo.pojo.RedisInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import io.lettuce.core.RedisConnectionException;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description Redis工具类实现
 * @ClassName RedisServiceImpl.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2021/1/27 16:39
 **/
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	JedisPool jedisPool;

	// 这也是换行符,功能和"\n"是一致的,但是此种写法屏蔽了
	// Windows 和 Linux 的区别 ，更保险一些
	private static String separator = System.getProperty("line.separator");

	private <T> T excuteByJedis(JedisExecutor<Jedis, T> j) throws RedisConnectionException {
		try (Jedis jedis = jedisPool.getResource()) {
			return j.excute(jedis);
		} catch (Exception e) {
			throw new RedisConnectionException(e.getMessage());
		}
	}

	@Override
	public List<RedisInfo> getRedisInfo() {
		String info = this.excuteByJedis(
				jedis -> {
					Client client = jedis.getClient();
					client.info();
					return client.getBulkReply();
				}
		);
		List<RedisInfo> infoList = new ArrayList<>();

		// 对获取的 Redis 客户端信息判空并分割
		String[] strs = Objects.requireNonNull(info).split(separator);

		RedisInfo redisInfo;
		if(strs.length > 0){
			for(String temp: strs){
				redisInfo = new RedisInfo();
				String[] str = temp.split(":");
				if (str.length > 1) {
					String key = str[0];
					String value = str[1];
					redisInfo.setKey(key);
					redisInfo.setValue(value);
					infoList.add(redisInfo);
				}
			}
		}

		return infoList;
	}

	@Override
	public Map<String, Object> getKeysSize() {
		long dbSize = this.excuteByJedis(
				jedis ->{
					Client client = jedis.getClient();
					client.dbSize();
					return client.getIntegerReply();
				}
		);
		Map<String, Object> map = new HashMap<>();
		map.put("create_time", System.currentTimeMillis());
		map.put("dbSize", dbSize);

		return map;
	}

	@Override
	public Map<String, Object> getMemoryInfo() {
		String info = this.excuteByJedis(
				jedis -> {
					Client client = jedis.getClient();
					client.info();
					return  client.getBulkReply();
				}
		);
		String[] strs = Objects.requireNonNull(info).split(separator);
		Map<String, Object> map = null;
		for(String temp: strs){
			String[] detail = temp.split(":");
			if("used_memory".equals(detail[0])){
				map = new HashMap<>();
				map.put("used_memory", detail[1].substring(0, detail[1].length()-1));
				map.put("create_time", System.currentTimeMillis());
				break;
			}
		}

		return map;
	}

	@Override
	public Set<String> getKeys(String pattern) {
		return this.excuteByJedis(jedis -> jedis.keys(pattern));
	}

	@Override
	public String get(String key) {
		return this.excuteByJedis(jedis -> jedis.get(key.toLowerCase()));
	}

	@Override
	public String set(String key, String value) {
		return this.excuteByJedis(jedis -> jedis.set(key.toLowerCase(), value));
	}

	@Override
	public String set(String key, String value, Long milliscends) {
		String result = this.set(key, value);
		this.pexpire(key, milliscends);
		return result;
	}

	@Override
	public Long del(String... key) {
		return this.excuteByJedis(jedis -> jedis.del(key));
	}

	@Override
	public Boolean exists(String key) {
		return this.excuteByJedis(jedis -> jedis.exists(key));
	}

	@Override
	public Long pttl(String key) {
		return this.excuteByJedis(jedis -> jedis.pttl(key));
	}

	@Override
	public Long pexpire(String key, Long milliscends) {
		return this.excuteByJedis(jedis -> jedis.pexpire(key, milliscends));
	}

	@Override
	public Long zadd(String key, Double score, String member) {
		return this.excuteByJedis(jedis -> jedis.zadd(key, score, member));
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		return this.excuteByJedis(jedis -> jedis.zrangeByScore(key, min, max));
	}

	@Override
	public Long zrem(String key, String... members) {
		return this.excuteByJedis(jedis -> jedis.zrem(key, members));
	}

	@Override
	public Long zremrangeByScore(String key, String min, String max) {
		return this.excuteByJedis(jedis -> jedis.zremrangeByScore(key, min, max));
	}
}
