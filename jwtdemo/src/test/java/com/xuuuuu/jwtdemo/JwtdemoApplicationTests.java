package com.xuuuuu.jwtdemo;

import com.xuuuuu.jwtdemo.pojo.Audience;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtdemoApplicationTests {
	@Autowired
	Audience audience;

	@Test
	void contextLoads() {
		System.out.print(audience);
	}

}
