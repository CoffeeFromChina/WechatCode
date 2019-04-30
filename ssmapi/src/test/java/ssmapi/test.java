package ssmapi;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuuuuu.domain.Category;
import com.xuuuuu.service.CategoryService;

public class test {
	
	@Autowired
	CategoryService categoryService;
	
	@Test
	public void test() {
		List<Category> listCategory = categoryService.getAll();
		listCategory.forEach(e -> System.out.println(e.getName()));
	}

}
