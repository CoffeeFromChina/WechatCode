package com.xuuuuu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xuuuuu.domain.Category;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

	List<Category> getAll();
}