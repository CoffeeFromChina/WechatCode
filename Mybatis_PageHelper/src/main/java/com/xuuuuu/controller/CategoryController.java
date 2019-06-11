package com.xuuuuu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuuuuu.pojo.Category;
import com.xuuuuu.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/list")
	public String listCategory(Model model,
	                           @RequestParam(defaultValue = "1",
			                           required = true,
			                           value = "pageNo") Integer pageNo) {

		Integer pageSize = 2;//每页显示记录数
		//分页查询
		PageHelper.startPage(pageNo, pageSize);
		List<Category> userList = categoryService.list();//获取所有用户信息

		PageInfo<Category> pageInfo = new PageInfo<Category>(userList);

		model.addAttribute("pageInfo", pageInfo);

		return "listCategory";
	}

//		//PageInfo包含了非常全面的分页属性
//		Assert.assertEquals(1,pageInfo.getPageNum());
//		Assert.assertEquals(10,pageInfo.getPageSize());
//		Assert.assertEquals(1,pageInfo.getStartRow());
//		Assert.assertEquals(10,pageInfo.getEndRow());
//		Assert.assertEquals(183,pageInfo.getTotal());
//		Assert.assertEquals(19,pageInfo.getPages());
//		Assert.assertEquals(1,pageInfo.getNavigateFirstPage());
//		Assert.assertEquals(8,pageInfo.getNavigateLastPage());
//		Assert.assertEquals(true,pageInfo.isIsFirstPage());
//		Assert.assertEquals(false,pageInfo.isIsLastPage());
//		Assert.assertEquals(false,pageInfo.isHasPreviousPage());
//		Assert.assertEquals(true,pageInfo.isHasNextPage());

}
