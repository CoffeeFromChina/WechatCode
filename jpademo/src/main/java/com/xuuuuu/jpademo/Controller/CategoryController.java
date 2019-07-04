package com.xuuuuu.jpademo.Controller;

import com.xuuuuu.jpademo.dao.CategoryDao;
import com.xuuuuu.jpademo.pojo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {
	@Autowired
	CategoryDao categoryDao;

	@RequestMapping(value = {"/", "index"})
	public String index(Model model,
	                     @RequestParam(defaultValue = "0",
			                     required = true,
			                     value = "pageNo") Integer pageNo){

		Integer pageSize = 2;//每页显示记录数。pageNo是第几页。
		Pageable pageable = new PageRequest(pageNo, pageSize);
		Page<Category> page = categoryDao.findAll(pageable);
		model.addAttribute("page", page);
		return "index";
	}

	@PostMapping("/add")
	public @ResponseBody
	List<Category> add(){

		//获取所有记录数量
		Long sum = categoryDao.count();
		//实例化Category对象。使用的时Lombok提供的构造方法。
		Category category = Category.builder()
									.name("category" + (sum+1))
									.build();
		//保存Category对象
		categoryDao.save(category);

		//获取所有数据
		List<Category> categoryList = categoryDao.findAll();
		return categoryList;
	}

	@DeleteMapping("/del/{id}")
	public @ResponseBody
	List<Category> del(@PathVariable("id") Long id){
		//根据Id删除Category对象
		categoryDao.deleteById(id);

		//获取所有数据
		List<Category> categoryList = categoryDao.findAll();
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
		//根据Id查找Category
		Category category = categoryDao.findCategoryById(id);
		//重新设置修改的字段信息
		category.setName(updateName);
		//更新数据
		categoryDao.save(category);

		//获取所有数据
		List<Category> categoryList = categoryDao.findAll();
		return categoryList;
	}
}
