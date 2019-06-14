package com.xuuuuu.dao;

import com.xuuuuu.pojo.Category;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

  public List<Category> list();

}
