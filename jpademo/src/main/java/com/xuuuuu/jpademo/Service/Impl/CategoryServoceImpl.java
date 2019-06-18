package com.xuuuuu.jpademo.Service.Impl;

import com.xuuuuu.jpademo.Service.CategoryService;
import com.xuuuuu.jpademo.dao.CategoryDao;
import com.xuuuuu.jpademo.pojo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServoceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getAll() {
		return categoryDao.findAll();
	}

	@Override
	public int getCount() {
		return (int) categoryDao.count();
	}

	@Override
	public void save(Category category) {
		categoryDao.save(category);
	}

	@Override
	public void delete(Long id) {
		categoryDao.deleteById(id);
	}

	@Override
	public void update(Long id, String updateName) {

		//根据Id查找Category
		Category category = categoryDao.findCategoryById(id);

		category.setName(updateName);

		categoryDao.save(category);

	}
}
