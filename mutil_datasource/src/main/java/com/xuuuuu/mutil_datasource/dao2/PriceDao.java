package com.xuuuuu.mutil_datasource.dao2;

import com.xuuuuu.mutil_datasource.pojo.Price;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceDao {
	public List<Price> getAll();
}
