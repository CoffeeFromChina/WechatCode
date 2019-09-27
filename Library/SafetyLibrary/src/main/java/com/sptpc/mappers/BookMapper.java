package com.sptpc.mappers;

import com.sptpc.pojo.Book;
import com.sptpc.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    public int insertBook(Book book);

    public List<Book> selectBook(Parameter parameter);

    public int deleteBookByID(String bkID);

    public int updateBookByID(Book book);
	//接口方法
    public int getCounts(Parameter parameter);
}
