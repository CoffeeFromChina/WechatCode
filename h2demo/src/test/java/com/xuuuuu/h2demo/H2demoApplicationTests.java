package com.xuuuuu.h2demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class H2demoApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Test
    public void testH2() throws SQLException {
        testDBConnection(); //测试数据库的链接
        testData();         //测试数据库的数据
    }

    public void testDBConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection.toString());
    }

    public void testData(){
        jdbcTemplate.queryForList("select * from category")
                .forEach(row -> System.out.println("Id：" + row.get("id") + "；" + "Name：" + row.get("name")));
    }

}
