package com.xuuuuu.annotationaspect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xuuuuu.annotationaspect.dao")
public class AnnotationascpctApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnotationascpctApplication.class, args);
	}

}
