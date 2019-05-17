package com.xuuuuu.mapper;

import com.xuuuuu.pojo.Catagory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatagoryMapper {
   List<Catagory> findAll();

   int insertCategory(Catagory catagory);
}
