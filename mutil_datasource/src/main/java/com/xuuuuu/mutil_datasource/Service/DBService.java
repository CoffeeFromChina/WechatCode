package com.xuuuuu.mutil_datasource.Service;

import com.xuuuuu.mutil_datasource.dao1.CategoryDao;
import com.xuuuuu.mutil_datasource.dao2.PriceDao;
import com.xuuuuu.mutil_datasource.pojo.Category;
import com.xuuuuu.mutil_datasource.pojo.Price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	PriceDao priceDao;

	public List<Category> getAllCategory(){
		return categoryDao.getAll();
	}

	public List<Price> getAllPrice(){
		return  priceDao.getAll();
	}
}
