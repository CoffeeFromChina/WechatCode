package com.xuuuuu.jwtdemo.config;

import com.xuuuuu.jwtdemo.interceptor.JwtInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 拦截器
 * @ClassName WebConfig.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/28 16:24
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

	// https://blog.csdn.net/ic_jvm/article/details/89634063?utm_medium=distribute.pc_relevant_right.none-task-blog-BlogCommendFromMachineLearnPai2-7.nonecase&depth_1-utm_source=distribute.pc_relevant_right.none-task-blog-BlogCommendFromMachineLearnPai2-7.nonecase
	// 这里是为例解决 JwtInterceptor 类中 Bean 无法注入的问题
	@Bean
	JwtInterceptor getJwtInterceptor(){
		return new JwtInterceptor();
	}

	// 添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截路径可自行配置多个 可用 ，分隔开
		registry.addInterceptor(getJwtInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/login")
				.excludePathPatterns("/css/**","/js/**","/images/**");//排除样式、脚本、图片等资源文件;
	}
	// 跨域支持
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
				.maxAge(3600 * 24);
	}
}
