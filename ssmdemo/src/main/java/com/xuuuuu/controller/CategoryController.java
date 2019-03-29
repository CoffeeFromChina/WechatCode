package com.xuuuuu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xuuuuu.domain.Category;
import com.xuuuuu.service.CategoryService;

// 告诉spring mvc这是一个控制器类
@Controller
// @RequestMapping("")
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @RequestMapping("/") // 设定请求路径。
  // Model的作用与Session相似
  public String listCategory(Model model) {
    List<Category> cs = categoryService.list();

    // 放入转发参数
    model.addAttribute("cs", cs);
    // 此处原本返回的是WEN-INF/jsp/listCategory.jsp。
    // 因为在spring-web.xml中设定了请求资源的前后缀，所以这里只需要写要返回的视图名称
    return "listCategory";
  }


  // 此方法与上面的方法作用相同。
  // @RequestMapping("/")
  // public ModelAndView listCategory(){
  // ModelAndView mav = new ModelAndView();
  // List<Category> cs= categoryService.list();
  //
  // // 放入转发参数
  // mav.addObject("cs", cs);
  // // 放入jsp路径
  // mav.setViewName("listCategory");
  // return mav;
  // }

}
