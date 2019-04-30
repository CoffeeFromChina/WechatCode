package com.xuuuuu.service;

import com.xuuuuu.dao.CategoryMapper;
import com.xuuuuu.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

	@Autowired
	CategoryMapper cm;

	public List<Category> getAll() {
		return cm.getAll();
	}

}
