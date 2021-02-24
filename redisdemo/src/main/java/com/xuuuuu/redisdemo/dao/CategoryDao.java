package com.xuuuuu.redisdemo.dao;

import com.xuuuuu.redisdemo.pojo.Category;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {
	//查找所有数据
	@Select("SELECT * FROM category")
	List<Category> getAll();
}
