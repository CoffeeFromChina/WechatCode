package com.xuuuuu.service;

import com.xuuuuu.pojo.Category;
import com.xuuuuu.dao.CategoryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService{
	@Autowired
	CategoryMapper categoryMapper;

	public List<Category> list(){
		return categoryMapper.list();
	}

}
