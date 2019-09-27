package com.sptpc.service;

import com.sptpc.mappers.BookMapper;
import com.sptpc.pojo.Book;
import com.sptpc.pojo.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//图书管理服务层
@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public int insertBook(Book book) {
        return this.bookMapper.insertBook(book);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Book> selectBook(Parameter parameter) {
        return this.bookMapper.selectBook(parameter);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteBookByID(String bkID) {
        return this.bookMapper.deleteBookByID(bkID);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateBookByID(Book book) {
        return this.bookMapper.updateBookByID(book);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public int getCounts(Parameter parameter) {
        return this.bookMapper.getCounts(parameter);
    }
}
