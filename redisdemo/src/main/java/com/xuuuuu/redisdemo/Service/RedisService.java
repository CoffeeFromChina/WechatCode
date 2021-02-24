package com.xuuuuu.redisdemo.Service;

import com.xuuuuu.redisdemo.pojo.RedisInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description Redis工具接口
 * @ClassName RedisService.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2021/1/27 16:39
 **/

public interface RedisService {

	// 获取 redis 的详细信息
	List<RedisInfo> getRedisInfo();

	//	获取 redis key 数量
	Map<String, Object> getKeysSize();

	// 获取 redis 内存信息
	Map<String, Object> getMemoryInfo();

	// 获取 key。参数为正则表达式
	Set<String> getKeys(String pattern);

	// 返回数据库中名称为 key 的 string 的 value
	String get(String key);

	// 给数据库中名称为 key 的 string 赋予值 value
	String set(String key, String value);

	// 给数据库中名称为 key 的 string 赋予值 value 并设置过期时间
	String set(String key, String value, Long milliscends);

	// 删除 key
	Long del(String ... key);

	// 确认一个key是否存在
	Boolean exists (String key);

	// 查询过期剩余时间，毫秒级别
	Long pttl(String key);

	// 指定经过多长时间过期,毫秒级别
	Long pexpire(String key, Long milliscends);

	// 向名称为key的zset中添加元素member，score用于排序。
	// 如果该元素已经存在，则根据score更新该元素的顺序。
	Long zadd(String key, Double score, String member);

	// 返回名称为key的zset中score >= min且score <= max的所有元素
	Set<String> zrangeByScore(String key, String min, String max);

	// 删除名称为key的zset中的元素member
	Long zrem(String key, String ... members);

	// 删除名称为key的zset中score >= min且score <= max的所有元素
	Long zremrangeByScore(String key, String min, String max);
}
