package com.xuuuuu.jpademo;

import com.xuuuuu.jpademo.dao.CategoryDao;
import com.xuuuuu.jpademo.pojo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpademoApplication implements ApplicationRunner {

	@Autowired
	CategoryDao categoryDao;

	public static void main(String[] args) {
		SpringApplication.run(JpademoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		injectionValue();
	}

	//向H2数据库插入值
	//因为Category的Id字段为自增长类型
	//所有不需要插入Id字段值
	public void injectionValue(){
		for(int i = 0; i < 5; i++){
			Category category = Category.builder()
					.name("categort" + (i+1))
					.build();
			categoryDao.save(category);
		}
	}

}
