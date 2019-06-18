package com.xuuuuu.jpademo.Service;

import com.xuuuuu.jpademo.pojo.Category;

import java.util.List;

public interface CategoryService {

	//获取所有数据
	public List<Category> getAll();

	//统计数据数量
	int getCount();

	//保持单条数据
	void save(Category category);

	//根据Id删除数据
	void delete(Long id);

	//更新单条数据
	void update(Long id, String updateName);
}
