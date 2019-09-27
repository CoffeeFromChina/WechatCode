package com.sptpc.controller;

import com.sptpc.pojo.BookClass;
import com.sptpc.pojo.Parameter;
import com.sptpc.service.BookClassService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//图书编目控制器
@Controller
@RequestMapping("/bookClass")
@RequiresRoles(value = {"BookManager", "SysManager", "Manager"}, logical = Logical.OR)
public class BookClassContorller {
    @Autowired
    private BookClassService bookClassService;

    //进入到图书编目的页面
    @RequestMapping("/toBookClass")
    @RequiresPermissions(value = {"bookClass:toBookClass", "iterm:all"}, logical = Logical.OR)
    public String comeBookClassJSp() {
        return "WEB-INF/bookJsp/bookClass";
    }

    //插入图书编目数据
    @RequestMapping(value = "/insertBookClass", method = {RequestMethod.POST, RequestMethod.GET})
    @RequiresPermissions(value = {"bookClass:insertBookClass", "iterm:all"}, logical = Logical.OR)
    public String InsertBookClass(@Validated BookClass bookClass, BindingResult br, Model model) {

        if (br.hasErrors()) {
            String Message = null;
            List<ObjectError> errors = br.getAllErrors();
            for (ObjectError objectError : errors) {

                model.addAttribute("message", Message + objectError.getDefaultMessage());
            }
            return "error";
        }
        bookClassService.insertBookClass(bookClass);
        model.addAttribute("bkCatalog", bookClass.getBkCatalog());
        model.addAttribute("bkClassName", bookClass.getBkClassName());
        return "WEB-INF/bookJsp/bookClass";
    }

    //查看所有图书编目信息
    @RequestMapping("/selectBookClass")
    @RequiresPermissions(value = {"bookClass:selectBookClass", "iterm:all"}, logical = Logical.OR)
    public String BookClassInfo(Parameter parameter, Model model) {
        int count = bookClassService.getCount(parameter);
        int start = parameter.getStart();
        if (start < 0) start = 0;
        if (start > count) start -= 10;
        int end = start + 10;
        parameter.setStart(start);
        parameter.setEnd(end);
        List<BookClass> bookClassList = bookClassService.selectBookClassByID(parameter);
        model.addAttribute("bookClassList", bookClassList);
        model.addAttribute("start", start);

        return "WEB-INF/bookJsp/bookClassInfo";
    }

    //删除图书编目信信息
    @RequestMapping("/deleteBookClass")
    @RequiresPermissions(value = {"bookClass:deleteBookClass", "iterm:all"}, logical = Logical.OR)
    public String DeleteBookClass(BookClass bookClass, RedirectAttributes ra, Parameter parameter) {
        bookClassService.deleteBookClassByID(bookClass);
        ra.addAttribute("start", parameter.getStart());
        return "redirect:/bookClass/selectBookClass";
    }

    //编辑图书编目信信息
    @RequestMapping("/editBookClass")
    @RequiresPermissions(value = {"bookClass:editBookClass", "iterm:all"}, logical = Logical.OR)
    public String EditBookClass(Parameter parameter, Model model) {
        BookClass bookClass = bookClassService.findBookClassByID(parameter);
        model.addAttribute("BookClass", bookClass);
        model.addAttribute("start", parameter.getStart());
        return "WEB-INF/bookJsp/bookClassInfo";
    }

    //更新图书编目信信息
    @RequestMapping(value = "/updateBookClass", method = {RequestMethod.POST, RequestMethod.GET})
    @RequiresPermissions(value = {"bookClass:updateBookClass", "iterm:all"}, logical = Logical.OR)
    public String UpdateBookClass(Parameter parameter, RedirectAttributes ra, @Validated BookClass bookClass, BindingResult br, Model model) {

        if (br.hasErrors()) {
            String Message = null;
            List<ObjectError> errors = br.getAllErrors();
            for (ObjectError objectError : errors) {

                model.addAttribute("message", Message + objectError.getDefaultMessage());
            }
            return "error";
        }
        bookClassService.updateBookClass(bookClass);
        ra.addAttribute("start", parameter.getStart());
        return "redirect:/bookClass/selectBookClass";

    }

}
