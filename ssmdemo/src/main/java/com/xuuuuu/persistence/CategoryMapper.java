package com.xuuuuu.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xuuuuu.domain.Category;

@Repository //声明此接口为数据访问组件。也就是标识这个接口是用来直接访问数据库的
public interface CategoryMapper {

  public int add(Category category);


  public void delete(int id);


  public Category get(int id);


  public int update(Category category);


  public List<Category> list();


  public int count();

}
