package com.xuuuuu.controller;

import com.xuuuuu.pojo.Category;
import com.xuuuuu.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @RequestMapping("/")
  public String listCategory(Model model) {
    List<Category> cs = categoryService.list();

    // 放入转发参数
    model.addAttribute("cs", cs);
    return "listCategory";
  }
}
