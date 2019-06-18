package com.xuuuuu.jpademo.Controller;

import com.xuuuuu.jpademo.Service.CategoryService;
import com.xuuuuu.jpademo.pojo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/")
	public @ResponseBody
	List<Category> index() {
		//获取所有数据
		List<Category> categoryList = categoryService.getAll();

		return categoryList;
	}

	@PostMapping("/add")
	public @ResponseBody
	List<Category> add(){

		//获取所有记录数量
		int sum = categoryService.getCount();

		//实例化Category对象
		Category category = Category.builder()
									.name("category" + (sum+1))
									.build();

		//保持Category对象
		categoryService.save(category);

		//获取所有数据
		List<Category> categoryList = categoryService.getAll();

		return categoryList;
	}

	@DeleteMapping("/del/{id}")
	public @ResponseBody
	List<Category> del(@PathVariable("id") Long id){

		//获取Id
		Long Id = id;

		//根据Id删除Category对象
		categoryService.delete(id);

		//获取所有数据
		List<Category> categoryList = categoryService.getAll();

		return categoryList;
	}


	//JPA是通过save()来进行插入和更新操作，判断依据是主键是否被赋值
	//如果主键被赋值，那么会查找主键数据，如果存在则执行更新操作，否则则执行插入操作。
	//在执行更新操作时，会覆盖所有属性。
	//https://www.cnblogs.com/suizhikuo/p/9927530.html
	@PatchMapping("/update/{id}/{name}")
	public @ResponseBody
	List<Category> update(@PathVariable("id") Long id,
	                      @PathVariable("name") String updateName){

		categoryService.update(id, updateName);

		//获取所有数据
		List<Category> categoryList = categoryService.getAll();

		return categoryList;
	}

}
