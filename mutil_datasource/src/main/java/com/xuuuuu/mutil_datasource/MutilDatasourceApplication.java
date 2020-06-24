package com.xuuuuu.mutil_datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

// 配置对数据源时必须排除以下配置类。
// 否则 Spring boot 会开启自动配置，从而影响我们配置多数据源
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class
})
public class MutilDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutilDatasourceApplication.class, args);
	}

}
