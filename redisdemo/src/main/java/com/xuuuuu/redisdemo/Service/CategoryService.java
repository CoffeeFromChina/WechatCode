package com.xuuuuu.redisdemo.Service;

import com.xuuuuu.redisdemo.dao.CategoryDao;
import com.xuuuuu.redisdemo.pojo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;

	public List<Category> getAll(){
		return categoryDao.getAll();
	}
}
