package com.xuuuuu.mutil_datasource.DataSourceConfig;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Druid 监控通用配置
 * @ClassName DruidConfig.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/24 14:53
 **/
@Configuration
public class DruidConfig {
	@Bean
	public ServletRegistrationBean druidStatViewServle() {
		//注册服务
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 白名单(为空表示,所有的都可以访问,多个IP的时候用逗号隔开)
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow)
		servletRegistrationBean.addInitParameter("deny", "127.0.0.2");
		// 设置登录的用户名和密码
		servletRegistrationBean.addInitParameter("loginUsername", "manager");
		servletRegistrationBean.addInitParameter("loginPassword", "manager");
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		// 添加过滤规则
		filterRegistrationBean.addUrlPatterns("/*");
		// 添加不需要忽略的格式信息
		filterRegistrationBean.addInitParameter("exclusions",
				"*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		System.out.println("druid初始化成功!");
		return filterRegistrationBean;
	}
}
