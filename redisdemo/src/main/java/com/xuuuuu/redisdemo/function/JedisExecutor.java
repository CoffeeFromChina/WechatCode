package com.xuuuuu.redisdemo.function;

import io.lettuce.core.RedisConnectionException;

/**
 * @Description 函数式接口
 * 他是一个接口，并且在这个接口中只能由一个抽象方法
 * 主要用在Lambda表达式和方法引用上
 * https://www.cnblogs.com/chenpi/p/5890144.html
 * @ClassName JedisExecutor.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2021/1/27 16:43
 **/
@FunctionalInterface
public interface JedisExecutor<T, R> {
	R excute(T t) throws RedisConnectionException;
}
