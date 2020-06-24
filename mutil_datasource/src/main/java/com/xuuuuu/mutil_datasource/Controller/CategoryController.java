package com.xuuuuu.mutil_datasource.Controller;

import com.xuuuuu.mutil_datasource.Service.DBService;
import com.xuuuuu.mutil_datasource.pojo.Category;
import com.xuuuuu.mutil_datasource.pojo.Price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {

	@Autowired
	DBService dbService;

	@RequestMapping(value = {"/", "index"})
	public String index(Model model){
		List<Category> userList = dbService.getAllCategory();   //获取所有用户信息
		List<Price> priceList = dbService.getAllPrice();        //获取所有价格信息

		model.addAttribute("userList", userList);
		model.addAttribute("priceList", priceList);

		return "index";
	}
}
