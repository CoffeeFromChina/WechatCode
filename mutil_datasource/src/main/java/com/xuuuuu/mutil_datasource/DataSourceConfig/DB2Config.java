package com.xuuuuu.mutil_datasource.DataSourceConfig;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Configuration
// 扫描dao包
@MapperScan(basePackages = {"com.xuuuuu.mutil_datasource.dao2"}, sqlSessionFactoryRef = "sqlSessionFactoryDb2")
public class DB2Config {
	private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

	@Value("${spring.datasource.druid.mulitsource2.jdbc-url:#{null}}")
	private String dbUrl;
	@Value("${spring.datasource.druid.mulitsource2.username: #{null}}")
	private String username;
	@Value("${spring.datasource.druid.mulitsource2.password:#{null}}")
	private String password;
	@Value("${spring.datasource.druid.mulitsource2.driver-class-name:#{null}}")
	private String driverClassName;
	@Value("${spring.datasource.initialSize:#{null}}")
	private Integer initialSize;
	@Value("${spring.datasource.minIdle:#{null}}")
	private Integer minIdle;
	@Value("${spring.datasource.maxActive:#{null}}")
	private Integer maxActive;
	@Value("${spring.datasource.maxWait:#{null}}")
	private Integer maxWait;
	@Value("${spring.datasource.timeBetweenEvictionRunsMillis:#{null}}")
	private Integer timeBetweenEvictionRunsMillis;
	@Value("${spring.datasource.minEvictableIdleTimeMillis:#{null}}")
	private Integer minEvictableIdleTimeMillis;
	@Value("${spring.datasource.validationQuery:#{null}}")
	private String validationQuery;
	@Value("${spring.datasource.testWhileIdle:#{null}}")
	private Boolean testWhileIdle;
	@Value("${spring.datasource.testOnBorrow:#{null}}")
	private Boolean testOnBorrow;
	@Value("${spring.datasource.testOnReturn:#{null}}")
	private Boolean testOnReturn;
	@Value("${spring.datasource.poolPreparedStatements:#{null}}")
	private Boolean poolPreparedStatements;
	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize:#{null}}")
	private Integer maxPoolPreparedStatementPerConnectionSize;
	@Value("${spring.datasource.filters:#{null}}")
	private String filters;
	@Value("{spring.datasource.connectionProperties:#{null}}")
	private String connectionProperties;

	@Bean(name = "db2")
	@ConfigurationProperties(prefix = "spring.datasource.druid.mulitsource2")
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(this.dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		//configuration
		if (initialSize != null) {
			datasource.setInitialSize(initialSize);
		}
		if (minIdle != null) {
			datasource.setMinIdle(minIdle);
		}
		if (maxActive != null) {
			datasource.setMaxActive(maxActive);
		}
		if (maxWait != null) {
			datasource.setMaxWait(maxWait);
		}
		if (timeBetweenEvictionRunsMillis != null) {
			datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		}
		if (minEvictableIdleTimeMillis != null) {
			datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		}
		if (validationQuery != null) {
			datasource.setValidationQuery(validationQuery);
		}
		if (testWhileIdle != null) {
			datasource.setTestWhileIdle(testWhileIdle);
		}
		if (testOnBorrow != null) {
			datasource.setTestOnBorrow(testOnBorrow);
		}
		if (testOnReturn != null) {
			datasource.setTestOnReturn(testOnReturn);
		}
		if (poolPreparedStatements != null) {
			datasource.setPoolPreparedStatements(poolPreparedStatements);
		}
		if (maxPoolPreparedStatementPerConnectionSize != null) {
			datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		}

		if (connectionProperties != null) {
			datasource.setConnectionProperties(connectionProperties);
		}

		List<Filter> filters = new ArrayList<>();
		filters.add(statFilter());
		filters.add(wallFilter());
		datasource.setProxyFilters(filters);

		return datasource;
	}

	// 慢 sql
	@Bean(name = "statFilter2")
	public StatFilter statFilter() {
		StatFilter statFilter = new StatFilter();
		statFilter.setLogSlowSql(true); //slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
		statFilter.setMergeSql(true); //SQL合并配置
		statFilter.setSlowSqlMillis(1000);//slowSqlMillis的缺省值为3000，也就是3秒。
		return statFilter;
	}

	@Bean(name = "wallFilter1")
	public WallFilter wallFilter() {
		WallFilter wallFilter = new WallFilter();
		//允许执行多条SQL
		WallConfig config = new WallConfig();
		config.setMultiStatementAllow(true);
		wallFilter.setConfig(config);
		return wallFilter;
	}

	@Autowired
	@Qualifier("db2")
	private DataSource dataSourceDb2;

	@Bean
	public SqlSessionFactory sqlSessionFactoryDb2() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSourceDb2);
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db2/*.xml"));
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplateDb2() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactoryDb2());
	}

}
