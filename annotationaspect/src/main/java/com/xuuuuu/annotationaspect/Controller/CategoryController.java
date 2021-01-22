package com.xuuuuu.annotationaspect.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuuuuu.annotationaspect.annotion.Log;
import com.xuuuuu.annotationaspect.pojo.Category;
import com.xuuuuu.annotationaspect.Service.CategoryService;

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

	@Log(value = "首页")
	@RequestMapping(value = {"/", "index"})
	public String index(Model model,
	                    @RequestParam(defaultValue = "1",
			                    required = true,
			                    value = "pageNo") Integer pageNo){

		Integer pageSize = 2;//每页显示记录数
		//分页查询
		//只对该语句以后的第一个查询语句得到的数据进行分页,
		PageHelper.startPage(pageNo, pageSize);

		List<Category> userList = categoryService.getAll();//获取所有用户信息
		PageInfo<Category> pageInfo = new PageInfo<Category>(userList);

		model.addAttribute("pageInfo", pageInfo);

		return "index";
	}
}
