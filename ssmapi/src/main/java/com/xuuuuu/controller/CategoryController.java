package com.xuuuuu.controller;

import com.xuuuuu.domain.Category;
import com.xuuuuu.domain.ResultBean;
import com.xuuuuu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ResultBean resultBean;
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public ResultBean index() {
		//获取所有目录信息
		List<Category> listCategory = categoryService.getAll();

		//在控制台输出所有信息
		listCategory.forEach(e -> System.out.println(e.getId() + " "));

		//设置JSON主体数据
		resultBean.setData(listCategory);

		//判断是否获取数据
		if(resultBean.getData() != null) {
			resultBean.setCode(200); //设置状态码
		}else {
			resultBean.setCode(500);
		}
		return resultBean;
	}
	
}
