package com.xuuuuu.mutil_datasource.dao1;

import com.xuuuuu.mutil_datasource.pojo.Category;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {
	public List<Category> getAll();
}
