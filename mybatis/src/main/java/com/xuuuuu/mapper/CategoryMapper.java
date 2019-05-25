package com.xuuuuu.mapper;

import com.xuuuuu.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryMapper {

    //查找所有数据
    @Select("SELECT * FROM category")
    List<Category> getAll();

    //通过id查找数据
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectById(int id);

    //插入数据
    @Insert("INSERT INTO category VALUES(#{id}, #{name})")
    void insert(int id, String name);

    //更新单个数据
    @Update("UPDATE category SET id = #{category.id}, name = #{category.name} WHERE id = #{oldId}")
    void update(@Param("category") Category category, @Param("oldId") int oldId);

}
