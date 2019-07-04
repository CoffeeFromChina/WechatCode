package com.xuuuuu.jpademo.dao;

import com.xuuuuu.jpademo.pojo.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//这里接口参数类型为「Long」
//那么所有其他的整数字段都要为Long
//否则在根据字段查找时会保错
public interface CategoryDao extends JpaRepository<Category, Long> {

	//命名规则参考文章
	//https://blog.csdn.net/sbin456/article/details/53304148

	//自定义的Pageable分页方法。
	Page<Category> findAll(Pageable pageable);

	//自定义的查找方法。只需根据JPA的命名规则创建即可
	//“nativeQuery=true”来表示是原生SQL
	@Query(value = "select * from category where id = ?",
			nativeQuery = true)
	Category findCategoryById(Long id);

}
