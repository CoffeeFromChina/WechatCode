package com.xuuuuu.jwtdemo.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Description 获取 JWT 参数
 * @ClassName Audience.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/27 16:16
 **/
@Data
@Component
@PropertySource(value = "classpath:application.properties")
public class Audience {
	@Value("${audience.clientId}")
	private String clientId;

	@Value("${audience.base64Secret}")
	private String base64Secret;

	@Value("${audience.name}")
	private String name;

	@Value("${audience.expiresSecond}")
	private long expiresSecond;
}
