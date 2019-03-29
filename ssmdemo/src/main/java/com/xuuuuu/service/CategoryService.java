package com.xuuuuu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuuuuu.domain.Category;
import com.xuuuuu.persistence.CategoryMapper;

@Service
public class CategoryService{
	@Autowired
	CategoryMapper categoryMapper;
	
	
	public List<Category> list(){
		return categoryMapper.list();
	};

}
