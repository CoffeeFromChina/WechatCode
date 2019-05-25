package com.xuuuuu.mybatis;

import com.xuuuuu.mapper.CategoryMapper;
import com.xuuuuu.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void testSelectAll() {
        List<Category> list = categoryMapper.getAll();
        list.forEach(e -> System.out.println(e.getId() + " " + e.getName()));
    }

    @Test
    public void testSelectById(){
        int id = 1;
        Category category = categoryMapper.selectById(id);
        System.out.println(category.getId() + " " + category.getName());
    }

    @Test
    public void testInsert(){
        int id = 6;
        String name = "category6";
        //插入数据
        categoryMapper.insert(id, name);

        //读取刚刚插入的数据
        Category category = categoryMapper.selectById(id);
        System.out.println(category.getId() + " " + category.getName());

        //更新刚刚插入的数据
        Category newCategory = new Category();
        newCategory.setId(7);
        newCategory.setName("category7");
        categoryMapper.update(newCategory, id);

        //读取刚刚更新的数据
        category = categoryMapper.selectById(newCategory.getId());
        System.out.println(category.getId() + " " + category.getName());
    }


}
